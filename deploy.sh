#!/bin/sh
mvn -q clean
mvn -q package
mvn -q jboss-as:deploy