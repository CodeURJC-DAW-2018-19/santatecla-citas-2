FROM openjdk:8

RUN apt-get update
RUN apt-get install -y maven

WORKDIR ./

ADD pom.xml /pom.xml
RUN ["mvn", "install"]
RUN ["mvn", "verify"]

ADD src /src
RUN ["mvn", "package"]

EXPOSE 8443
CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "target/practica.jar"]
