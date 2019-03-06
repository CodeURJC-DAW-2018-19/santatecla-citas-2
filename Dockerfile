FROM openjdk:8

WORKDIR /src

COPY target/practica-0.0.1-SNAPSHOT.jar.original practica.jar

EXPOSE 8443

CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "practica.jar"]
