#!/bin/bash
sudo docker run -it --rm --name practica -v "$(pwd)":/src -w /src maven:3.3-jdk-8 mvn clean install package
docker build
