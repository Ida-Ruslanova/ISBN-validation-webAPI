#!/bin/sh

echo "Build ISBN project" 

mvn clean install

echo "Start ISBN application"

cd  target/

java -jar isbnapi-0.0.1-SNAPSHOT.jar

echo "DONE!!!"

