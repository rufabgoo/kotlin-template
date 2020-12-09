#!/usr/bin/env bash

for f in $(find . -type f -name "*.properties"); do
    mv "${f}" "${f}.j2"
    j2 "${f}.j2" > "${f}"
done

java -jar demo-back.jar
