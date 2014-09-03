package com.ivan.hadoop.scalding

import com.twitter.scalding._

class MinMaxAvgJob(args : Args) extends Job(args) {
  val source = Csv( args("input"), " ", ('id, 'timestamp, 'value) )
  val sink = Tsv( args("output") )

  // produce 'm0 = minute rounded timestamp
  val p0 = source.read.map('timestamp -> 'm0) { t: Long => Math.round(t / 60000.0) * 60000}

  // group by minute,
  // aggregate by min, avg, max: ('m0, 'val) -> ('m1, 'min), ('m2, 'avg), ('m3, 'max)
  // TODO: write single (min, avg, max) groupAggregator
  val p1 = p0.groupBy('m0) { _.min('value) }.rename(('m0, 'value) -> ('m1, 'min))
  val p2 = p0.groupBy('m0) { _.average('value) }.rename(('m0, 'value) -> ('m2, 'avg))
  val p3 = p0.groupBy('m0) { _.max('value) }.rename(('m0, 'value) -> ('m3, 'max))

  // left join 'm1, 'm2, 'm3 pipes back into single one
  // and write down the result
  val p4 =
    p1.leftJoinWithSmaller('m1 -> 'm2, p2)
      .leftJoinWithSmaller('m2 -> 'm3, p3)
      .project('m1, 'min, 'avg, 'max)
      .write(sink)
}
