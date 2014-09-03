hbase-cascading-test1
=====================

Sample data processing pipeline using cascading / scalding / hbase. Stream double -> (min, max, avg)

Usage
=====

1. install hbase docker instance using [dajobe hbase 0.94.11 docker instance](https://github.com/dajobe/hbase-docker).
2. build your cascading (2.5), cascading-hbase (gradle ~1.8), scalding (0.11.2)
3. try to install data into hbase using `data/insert.py`
