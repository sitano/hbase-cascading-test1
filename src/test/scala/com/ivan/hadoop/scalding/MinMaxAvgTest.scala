package com.ivan.hadoop.scalding

import org.specs2.mutable.Specification

import com.twitter.scalding._

class MinMaxAvgTest extends Specification {
  "A MinMaxAvg job" should {
    JobTest("com.ivan.hadoop.scalding.MinMaxAvgJob").
      arg("input", "inputFile").
      arg("output", "outputFile").
      source(Csv( "inputFile"), List(
        ("gpi.mech.battle.14729", 1409691301000L, 0.9607714118860488),
        ("gpi.mech.battle.14730", 1409691301000L, 1.0369443119636337),
        ("gpi.mech.battle.14756", 1409694900000L, 0.9481745203307692),
        ("gpi.mech.battle.14757", 1409694900000L, 1.2123569905536158))).
      sink[(Long,Double,Double,Double)](Tsv("outputFile")){ outputBuffer =>
        val list = outputBuffer.toList
        System.out.print(list)
        for (t <- list) {
          "correctly at " + t._1 in {
            t._2 must be_< (t._3)
            t._3 must be_< (t._4)
          }
        }
      }.
      run.
      finish
  }
}
