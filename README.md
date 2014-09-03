HBase Cascading/Scalding example
================================

Sample data processing pipeline using cascading / scalding / hbase. Stream double -> (min, max, avg)

Usage
=====

1. install hbase docker instance using [dajobe hbase 0.94.11 docker instance](https://github.com/dajobe/hbase-docker).
2. (optional) build your [cascading](https://github.com/Cascading/cascading) (2.5), [cascading-hbase](https://github.com/Cascading/cascading.hbase) ([gradle](https://services.gradle.org/distributions/gradle-1.8-all.zip) ~1.8), [scalding](https://github.com/twitter/scalding) (0.11.2)
3. try to install data into hbase using `data/insert.py`

Building
========

    $ git clone https://github.com/sitano/hbase-cascading-test1.git
    $ cd hbase-cascading-test1
    $ ./sbt assembly

The 'fat jar' is now available as:

    target/hbase-cascading-test1-0.0.1.jar

Unit testing
============

The `assembly` command above runs the test suite - but you can also run this manually with:

    $ ./sbt test

Scalding local run
==================

    ./scripts/scald.rb --local ~/Projects/hbase-cascading-test1/src/main/scala/com/ivan/hadoop/scalding/MinMaxAvgJob.scala --input ~/Projects/hbase-cascading-test1/data/data-den-2014-09-03 --output ~/Projects/hbase-cascading-test1/data/data-den-2014-09-03.out

Hadoop / HBase run
==================

    TODO

TODO
====

* do not produce 3 new pipes to do aggregration. use custom aggregator instead.

See also
========

* [snowplow/scalding-example-project](https://github.com/snowplow/scalding-example-project)
* [snowplow/spark-example-project](https://github.com/snowplow/spark-example-project)

To read
=======

* [This is the official reference guide of Apache HBase™](http://hbase.apache.org/book.html)
* [HappyBase is a developer-friendly Python library to interact with Apache HBase](http://happybase.readthedocs.org/en/latest/)
* [Cascading 2.5 User Guide](http://docs.cascading.org/cascading/2.5/userguide/htmlsingle/)
* [Hadoop version support matrix to HBase](http://hbase.apache.org/book.html#hadoop)

Copyright and license
=====================

The MIT License (MIT)

Copyright (c) 2014 Ivan Prisyazhniy <john.koepi@gmail.com>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the “Software”), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

