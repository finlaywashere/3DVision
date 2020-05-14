#!/bin/bash
# Publishes built artifacts to github
./gradlew publish -PbuildType=linux-x86_64 -PtestType=linux-x86_64 $@
