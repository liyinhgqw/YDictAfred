#! /usr/bin/env bash

bin=`dirname "$0"`
bin=`cd "$bin"; pwd`

if [ $# = 0 ]; then
  echo 'Usage: look WORDS'
  exit 1
fi

WORDS=$1
java -jar "$bin"/Dict-*-with-*.jar $WORDS

