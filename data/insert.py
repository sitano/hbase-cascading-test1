#!/usr/bin/python
# pip install happybase
import happybase

lines = [line.strip() for line in open('data-2014-09-03')]

connection = happybase.Connection('hbase-docker', 9090)

if 'match' in connection.tables():
    print '<Match> table already exists. I will do nothing.'
    exit(1)

connection.create_table('match', {'data': dict()})

table = connection.table('match')
for line in lines:
  t = tuple(line.split())
  table.put(t[0], {'data:score': t[2], 'data:density': t[3]}, timestamp = int(t[1]) * 1000)

print 'All data successfully written into <Match> table.'
