# Build from the Java 8 maven image (include JDK 8 and Maven)
FROM maven:3.3-jdk-8
MAINTAINER Troy Washburn <troy.washburn@rentacenter.com>

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY pom.xml /usr/src/app/
COPY src/ /usr/src/app/src/

RUN mvn package

COPY log4j.properties /usr/src/app/

CMD ["java","-classpath",".:target/lib/log4j-1.2.17.jar:target/lib/logzio-log4j-appender-1.0.1.jar:target/lib/slf4j-api-1.7.22.jar:target/lib/slf4j-log4j12-1.7.22.jar:target/logzio-log4j-example-1.0-SNAPSHOT.jar","com.rentacenter.examples.LogzioLog4jExample"]
