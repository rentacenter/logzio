# Build from the Java 8 maven image (include JDK 8 and Maven)
FROM maven:3.3-jdk-8
MAINTAINER Troy Washburn <troy.washburn@rentacenter.com>

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY pom.xml /usr/src/app/
COPY src/ /usr/src/app/src/

RUN mvn package

CMD ["java","-classpath","target/logzio-bulk-example-1.0-SNAPSHOT.jar:target/lib/jersey-core-1.9.1.jar:target/lib/jersey-client-1.9.1.jar:target/lib/json-20160810.jar","com.rentacenter.examples.LogzioBulkExample"]
