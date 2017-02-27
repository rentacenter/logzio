# Tomcat 7 example
This example shows how to use the [Logz.io Log4j 1.2 Appender](https://github.com/logzio/logzio-log4j-appender) inside your Tomcat version 7 application server to centralize your logs.

While this example prints logs both to the console & remotely to Logz.io, the end goal should just be to send remote logs to Logz.io and not to the console or a file.  This will have the best performance of minimizing I/O, as well as the benefit of not having to manage log rotations or disk space.

### High level steps
At a high level, here are the steps (review [Dockerfile](Dockerfile) for more specifics):
- Convert Tomcat to use the Log4j framework
  - Install the JULI libraries
  - Install the log4j library
  - Remove the default logging.properties file
- Install the Logz.io libraries and dependencies (review [pom.xml](pom.xml))
- Update your *log4j.properties* file to include the *LogzioAppender* and desired properties (review [log4j.properties](log4j.properties))

### Build the example
```shell
$ docker build -t logz/tomcat7-example:1 .
```

### Run the example
```shell
$ docker run -e 'LOGZIO_TOKEN=<put your logz.io token here>' -e 'ENVIRONMENT=dev' logz/tomcat7-example:1
```
