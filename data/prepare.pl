#!/usr/bin/perl -n
use Date::Parse;
/profiler[-_0-9]+:([\/0-9]+ [:.0-9]+).*(gpi.mech.battle.[0-9]+).*relScr=([.0-9]+).*relDen=([.0-9]+)/ && print "$2 ", str2time($1), " $3 $4\n"
