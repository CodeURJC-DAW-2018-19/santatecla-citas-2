FROM openjdk:8-jdk-alpine

WORKDIR /

COPY backend/target/practica-0.0.1-SNAPSHOT.jar practica.jar
#COPY /target/start2.sh start2.sh
#RUN chmod 777 start2.sh

EXPOSE 8443

CMD ["java","-jar","practica.jar"]
