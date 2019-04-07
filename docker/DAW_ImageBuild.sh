#!/bin/bash
cd ..
pwd
cd ng
ng build 
ls
cd dist
cp -r my-app/. ../../backend/src/main/resources/static/new
ls
cd ..
cd ..
cd backend
pwd
sudo docker run -it --rm --name practica -v "$(pwd)":/src -w /src maven:3.3-jdk-8 mvn clean install package
cd ..
pwd
sudo docker build -t app .
