# Logz.io Log Shipping Examples

[Logz.io](http://logz.io) is a secure & private enterprise-grade log analytics platform utilizing [ELK](https://www.elastic.co/products) (the world's most popular open-source data analytics platform).  With Logz.io, you can easily index, search, analyze and visualize pretty much any data you want.

## Best Practices

Below is simply a list of best practices to be referenced:

- Send log data from **all sources** (e.g. servers, applications, devices).  The power of ELK is to centralize and analyze events across the spectrum allowing a full picture view for visibility and troubleshooting.
- Logz.io is **licensed by the amount of data** ingested on a daily basis.  If you exceed the limit too far, the data will not be ingested into the platform.  (i.e. Don't turn on debug mode if it is not necessary.)
- Consider logging additional fields (e.g. **environment**, **application**, **source_system**) to filter logs at a high level specific to your environment and application (e.g. *environment:prod AND application:logzioexamples AND source_system:esb*).
- The goal should be to **use visualizations and dashboards** to identify trends and look for meaningful metrics proactively, before incidents are reported.  You can also set up alerts to assist with this.
- **Secure** [log shipping](https://app.logz.io/#/dashboard/data-sources/) methods should be utilized for increased security when needed.
- Ship **[JSON formatted logs](https://app.logz.io/#/dashboard/data-sources/JSON)** to easily control field names, field types, and log types.

## Log Shipping Examples

The examples below have all been [dockerized](https://www.docker.com/) to greatly simplify pulling them down & running them.

**NOTICE:** Log4j 1.2 has End of Life as of August 5th, 2015 (https://logging.apache.org/log4j/1.2/download.html).  In addition, there is a known deadlock situation when using with Bigqueue; Logz.io has deprecated their appenders that utilize Log4j 1.2.  Instead, convert to using Log4j 2.x or Logback as an alternative.

- [x] [Log4j 1.2 Appender](log4j-example) example (Standalone Java application using log4j 1.2 - Logz.io [instructions](https://app.logz.io/#/dashboard/data-sources/Java--log4jappender))
- [x] [Log4j 1.2 Appender with Java 7](log4j-jre7-example) example (Standalone Java application using log4j 1.2 - Logz.io [instructions](https://app.logz.io/#/dashboard/data-sources/Java--log4jappender))
- [x] [Log4j 2 Appender](log4j2-example) example (Standalone Java application using log4j 2 - Logz.io [instructions](https://app.logz.io/#/dashboard/data-sources/Java--log4j2appender))
- [x] [Spring Web Application](spring-webapp-example) example (Simple Spring application deployed to Tomcat 7 using log4j 1.2)
- [x] [Tomcat 7](tomcat7-example) example (Tomcat 7 application server using log4j 1.2)
- [x] [Tomcat 7 / Web Application](tomcat7-webapp-example) example (Tomcat 7 application server using log4j 1.2 bundled with Web Application deployed using unique log4j config)
- [x] [Tomcat 8](tomcat8-example) example (Tomcat 8 application server using log4j 1.2)
- [x] [Tomcat 8.5](tomcat85-example) example (Tomcat 8.5 application server using log4j 2)
- [x] [Rsyslog over TLS](rsyslog-tls-example) example (UN*X rsyslog configuration - Logz.io [instructions](https://app.logz.io/#/dashboard/data-sources/rsyslog-overTLS))
- [x] [Web Services/Bulk](bulk-example) example (Logz.io [instructions](https://app.logz.io/#/dashboard/data-sources/Bulk-HTTPS))
- [x] [Java Sender](java-sender-example) example (Uses Logz.io Java Sender when log4j isn't an option - Logz.io [instructions](https://github.com/logzio/logzio-java-sender))
- [ ] logstash example (Logz.io [instructions](https://app.logz.io/#/dashboard/data-sources/logstash))
- [ ] beats examples (Logz.io [instructions](https://app.logz.io/#/dashboard/data-sources/Beats))
 - [ ] nginx example (Logz.io [instructions](https://app.logz.io/#/dashboard/data-sources/nginx))
 - [ ] apache example (Logz.io [instructions](https://app.logz.io/#/dashboard/data-sources/apache))
- [x] [Docker Metrics](docker-example) example (Forward Docker metrics and logs using Logz.io [instructions](https://app.logz.io/#/dashboard/data-sources/Docker-Logging))
