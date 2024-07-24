#!/usr/bin/env sh

export GRAALVM_HOME="/home/mr_sena/Lib/binaries-compose/GraalVm/graalvm-jdk-17.0.12+8.1/"
./gradlew nativeCompile --info
