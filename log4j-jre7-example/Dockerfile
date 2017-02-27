# Build from the Java 8 maven image (include JDK 8 and Maven)
FROM maven:3.3-jdk-7
MAINTAINER Troy Washburn <troy.washburn@rentacenter.com>

# Make & use working directory
RUN mkdir -p /usr/src/app/lib
WORKDIR /usr/src/app

# Pull down Logz.io Java Sender, compile, & package
RUN git clone https://github.com/logzio/logzio-java-sender.git && \
    cd logzio-java-sender && \
    sed -i 's/<source>1\.8<\/source>/<source>1.7<\/source>/' pom.xml && \
    sed -i 's/<target>1\.8<\/target>/<target>1.7<\/target>/' pom.xml && \
    mvn package && \
    cp logzio-sender/target/logzio-sender-1.0.5.jar /usr/src/app/lib

# Pull down Logz.io Log4j Appender, compile, & package
RUN git clone https://github.com/logzio/logzio-log4j-appender.git && \
    cd logzio-log4j-appender && \
    sed -i 's/<source>1\.8<\/source>/<source>1.7<\/source>/' pom.xml && \
    sed -i 's/<target>1\.8<\/target>/<target>1.7<\/target>/' pom.xml && \
    sed -i 's/<version>${logzio-sender-version}<\/version>/<version>${logzio-sender-version}<\/version><systemPath>\/usr\/src\/app\/lib\/logzio-sender-1.0.5.jar<\/systemPath>/' pom.xml && \
    mvn package && \
    cp logzio-log4j-appender/target/logzio-log4j-appender-1.0.1.jar /usr/src/app/lib

# Compile & package example application
COPY pom.xml /usr/src/app/
COPY src/ /usr/src/app/src/
RUN mvn package

COPY log4j.properties /usr/src/app/

CMD ["java","-classpath",".:target/lib/log4j-1.2.17.jar:lib/logzio-log4j-appender-1.0.1.jar:target/lib/slf4j-api-1.7.22.jar:target/lib/slf4j-log4j12-1.7.22.jar:target/logzio-log4j-jre7-example-1.0-SNAPSHOT.jar","com.rentacenter.examples.LogzioLog4jJre7Example"]
