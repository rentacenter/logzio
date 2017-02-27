# Build from the Tomcat 8.5 with JRE 8 image
FROM tomcat:8.5-jre8
MAINTAINER Troy Washburn <troy.washburn@rentacenter.com>

ARG MAVEN_VERSION=3.3.9

# build web-application
RUN mkdir -p /usr/src/app
COPY pom.xml /usr/src/app/
WORKDIR /usr/src/app

# Download Logz.io Log4j 2 appender & dependencies with Maven
RUN set -x \
  && mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL https://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz \
      | tar -xzC /usr/share/maven --strip-components=1 \
  && /usr/share/maven/bin/mvn package \
  && cp target/lib/log*.jar target/lib/slf4j* $CATALINA_HOME/lib \
  && rm -fr target \
  && rm -fr /usr/share/maven

ENV LOGGING_CONFIG -Dnop
ENV LOGGING_MANAGER -Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager

RUN rm $CATALINA_HOME/conf/logging.properties
COPY log4j2.xml $CATALINA_HOME/conf
COPY setenv.sh $CATALINA_HOME/bin
