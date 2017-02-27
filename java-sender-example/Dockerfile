# Build from the Java 8 maven image (include JDK 8 and Maven)
FROM maven:3.3-jdk-8
MAINTAINER Troy Washburn <troy.washburn@rentacenter.com>

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY pom.xml /usr/src/app/
COPY src/ /usr/src/app/src/

RUN mvn package

CMD ["java","-classpath","target/logzio-java-sender-example-1.0-SNAPSHOT.jar:target/lib/logzio-sender-1.0.5.jar:target/lib/slf4j-api-1.7.22.jar:target/lib/slf4j-simple-1.7.22.jar","com.rentacenter.examples.LogzioJavaSenderExample"]
