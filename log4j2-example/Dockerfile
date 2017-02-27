# Build from the Java 8 maven image (include JDK 8 and Maven)
FROM maven:3.3-jdk-8
MAINTAINER Troy Washburn <troy.washburn@rentacenter.com>

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY pom.xml /usr/src/app/
COPY src/ /usr/src/app/src/

RUN mvn package

COPY log4j2.xml pom.xml /usr/src/app/

CMD ["java","-classpath",".:target/lib/log4j-api-2.8.jar:target/lib/log4j-core-2.8.jar:target/lib/log4j-slf4j-impl-2.8.jar:target/lib/logzio-log4j2-appender-1.0.2.jar:target/lib/slf4j-api-1.7.22.jar:target/logzio-log4j2-example-1.0-SNAPSHOT.jar","com.rentacenter.examples.LogzioLog4j2Example"]
