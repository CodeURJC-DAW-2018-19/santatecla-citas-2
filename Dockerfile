FROM openjdk:8-jdk-alpine

WORKDIR /src

COPY target/practica-0.0.1-SNAPSHOT.jar practica.jar

EXPOSE 8443

CMD ["java", "-jar", "practica.jar"]
