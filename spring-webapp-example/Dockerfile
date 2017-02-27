# Build from the Java 8 maven image (include JDK 8 and Maven)
FROM maven:3.3-jdk-8
MAINTAINER Troy Washburn <troy.washburn@rentacenter.com>

ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH
RUN mkdir -p "$CATALINA_HOME"
WORKDIR $CATALINA_HOME

# see https://www.apache.org/dist/tomcat/tomcat-$TOMCAT_MAJOR/KEYS
# see also "update.sh" (https://github.com/docker-library/tomcat/blob/master/update.sh)
ENV GPG_KEYS 05AB33110949707C93A279E3D3EFE6B686867BA6 07E48665A34DCAFAE522E5E6266191C37C037D42 47309207D818FFD8DCD3F83F1931D684307A10A5 541FBE7D8F78B25E055DDEE13C370389288584E7 61B832AC2F1C5A90F0F9B00A1C506407564C17A3 713DA88BE50911535FE716F5208B0AB1D63011C7 79F7026C690BAA50B92CD8B66A3AD3F4F22C4FED 9BA44C2621385CB966EBA586F72C284D731FABEE A27677289986DB50844682F8ACB77FC2E86E29AC A9C5DF4D22E99998D9875A5110C01C5A2F6059E7 DCFD35E0BF8CA7344752DE8B6FB21E8933C60243 F3A04C595DB5B6A5F1ECA43E3B7BBB100D811BBE F7DA48BB64BCB84ECBA7EE6935CD23C10D498E23
RUN set -ex; \
  for key in $GPG_KEYS; do \
    gpg --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys "$key"; \
  done

ENV TOMCAT_MAJOR 7
ENV TOMCAT_VERSION 7.0.75

# https://issues.apache.org/jira/browse/INFRA-8753?focusedCommentId=14735394#comment-14735394
ENV TOMCAT_TGZ_URL https://www.apache.org/dyn/closer.cgi?action=download&filename=tomcat/tomcat-$TOMCAT_MAJOR/v$TOMCAT_VERSION/bin/apache-tomcat-$TOMCAT_VERSION.tar.gz
# not all the mirrors actually carry the .asc files :'(
ENV TOMCAT_ASC_URL https://www.apache.org/dist/tomcat/tomcat-$TOMCAT_MAJOR/v$TOMCAT_VERSION/bin/apache-tomcat-$TOMCAT_VERSION.tar.gz.asc

RUN set -x \
  \
  && wget -O tomcat.tar.gz "$TOMCAT_TGZ_URL" \
  && wget -O tomcat.tar.gz.asc "$TOMCAT_ASC_URL" \
  && gpg --batch --verify tomcat.tar.gz.asc tomcat.tar.gz \
  && tar -xvf tomcat.tar.gz --strip-components=1 \
  && rm bin/*.bat \
  && rm tomcat.tar.gz* \
  && rm bin/tomcat-native.tar.gz

# build web-application
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY pom.xml /usr/src/app/
COPY src/ /usr/src/app/src/

RUN mvn package \
  && cp /usr/src/app/target/spring-webapp-example.war $CATALINA_HOME/webapps

# custom start script
COPY run.sh $CATALINA_HOME/bin/

CMD ["run.sh"]
