# Tomcat 8.5 example
This example shows how to use the [Logz.io Log4j 2 Appender](https://github.com/logzio/logzio-log4j2-appender) inside your Tomcat version 8.5 application server to centralize your logs.

While this example prints logs both to the console & remotely to Logz.io, the end goal should just be to send remote logs to Logz.io and not to the console or a file.  This will have the best performance of minimizing I/O, as well as the benefit of not having to manage log rotations or disk space.

### High level steps
At a high level, here are the steps (review [Dockerfile](Dockerfile) for more specifics):
- Convert Tomcat to use the Log4j 2 framework
  - Define LOGGING_CONFIG & LOGGING_MANAGER environment variables (review [Dockerfile](Dockerfile))
  - Update CLASSPATH (review [setenv.sh](setenv.sh))
  - Remove the default logging.properties file
- Install the Logz.io libraries and dependencies (review [pom.xml](pom.xml))
- Install/update your *log4j2.xml* file to include the *LogzioAppender* and desired properties (review [log4j2.xml](log4j2.xml))

### Build the example
```shell
$ docker build -t logz/tomcat85-example:1 .
```

### Run the example
```shell
$ docker run -e 'LOGZIO_TOKEN=<put your logz.io token here>' -e 'ENVIRONMENT=dev' logz/tomcat85-example:1
```
