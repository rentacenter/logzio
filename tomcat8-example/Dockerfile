# Build from the Tomcat 8 with JRE 8 image
FROM tomcat:8.0.41-jre8
MAINTAINER Troy Washburn <troy.washburn@rentacenter.com>

ARG TOMCAT_MAJOR=8
ARG TOMCAT_VERSION=8.0.41
ARG LOG4J_VERSION=1.2.17
ARG MAVEN_VERSION=3.3.9
ARG LOGZ_LOG4J_VERSION=1.0.1
ARG SLF4J_VERSION=1.7.22

# https://issues.apache.org/jira/browse/INFRA-8753?focusedCommentId=14735394#comment-14735394
ARG TOMCAT_JULI_JAR_URL=https://www.apache.org/dyn/closer.cgi?action=download&filename=tomcat/tomcat-$TOMCAT_MAJOR/v$TOMCAT_VERSION/bin/extras/tomcat-juli.jar
# not all the mirrors actually carry the .asc files :'(
ARG TOMCAT_JULI_ASC_URL=https://www.apache.org/dist/tomcat/tomcat-$TOMCAT_MAJOR/v$TOMCAT_VERSION/bin/extras/tomcat-juli.jar.asc

# Download, validate, and install JULI log4j library
RUN set -x \
  && wget -O $CATALINA_HOME/bin/tomcat-juli.jar "$TOMCAT_JULI_JAR_URL" \
  && wget -O tomcat-juli.jar.asc "$TOMCAT_JULI_ASC_URL" \
  && gpg --batch --verify tomcat-juli.jar.asc $CATALINA_HOME/bin/tomcat-juli.jar \
  && rm tomcat-juli.jar.asc

# https://issues.apache.org/jira/browse/INFRA-8753?focusedCommentId=14735394#comment-14735394
ARG TOMCAT_JULI_ADAPTERS_JAR_URL=https://www.apache.org/dyn/closer.cgi?action=download&filename=tomcat/tomcat-$TOMCAT_MAJOR/v$TOMCAT_VERSION/bin/extras/tomcat-juli-adapters.jar
# not all the mirrors actually carry the .asc files :'(
ARG TOMCAT_JULI_ADAPTERS_ASC_URL=https://www.apache.org/dist/tomcat/tomcat-$TOMCAT_MAJOR/v$TOMCAT_VERSION/bin/extras/tomcat-juli-adapters.jar.asc

# Download, validate, and install JULI adapters library
RUN set -x \
  && wget -O $CATALINA_HOME/lib/tomcat-juli-adapters.jar "$TOMCAT_JULI_ADAPTERS_JAR_URL" \
  && wget -O tomcat-juli-adapters.jar.asc "$TOMCAT_JULI_ADAPTERS_ASC_URL" \
  && gpg --batch --verify tomcat-juli-adapters.jar.asc $CATALINA_HOME/lib/tomcat-juli-adapters.jar \
  && rm tomcat-juli-adapters.jar.asc

# https://issues.apache.org/jira/browse/INFRA-8753?focusedCommentId=14735394#comment-14735394
ARG LOG4J_TGZ_URL=https://www.apache.org/dyn/closer.cgi?action=download&filename=logging/log4j/$LOG4J_VERSION/log4j-$LOG4J_VERSION.tar.gz
# not all the mirrors actually carry the .asc files :'(
ARG LOG4J_ASC_URL=https://www.apache.org/dist/logging/log4j/$LOG4J_VERSION/log4j-$LOG4J_VERSION.tar.gz.asc
ARG LOG4J_KEYS_URL=https://www.apache.org/dist/logging/KEYS

# Download, validate, and install log4j library
RUN set -x \
  && wget -O log4j.tar.gz "$LOG4J_TGZ_URL" \
  && wget -O log4j.tar.gz.asc "$LOG4J_ASC_URL" \
  && wget -O KEYS "$LOG4J_KEYS_URL" \
  && gpg --import KEYS \
  && gpg --batch --verify log4j.tar.gz.asc log4j.tar.gz \
  && tar -xvf log4j.tar.gz \
  && cp apache-log4j-$LOG4J_VERSION/log4j-$LOG4J_VERSION.jar $CATALINA_HOME/lib \
  && rm -r apache-log4j-$LOG4J_VERSION \
  && rm log4j.tar.gz* \
# Delete default logging properties to prevent JUL generating zero length log files
  && rm $CATALINA_HOME/conf/logging.properties

# build web-application
RUN mkdir -p /usr/src/app
COPY pom.xml /usr/src/app/
WORKDIR /usr/src/app

# Download Logz.io Log4j 1.2 appender with Maven
RUN set -x \
  && mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL https://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz \
      | tar -xzC /usr/share/maven --strip-components=1 \
  && /usr/share/maven/bin/mvn package \
  && cp target/lib/logzio-log4j-appender-$LOGZ_LOG4J_VERSION.jar $CATALINA_HOME/lib \
  && cp target/lib/slf4j-api-$SLF4J_VERSION.jar $CATALINA_HOME/lib \
  && cp target/lib/slf4j-log4j12-$SLF4J_VERSION.jar $CATALINA_HOME/lib \
  && rm -fr target \
  && rm -fr /usr/share/maven

COPY log4j.properties $CATALINA_HOME/lib
