# Log4j 2 Appender example
This example shows how to use the [Logz.io Log4j 2 Appender](https://github.com/logzio/logzio-log4j2-appender) to centralize your Java logs.

While this example prints logs both to the console & remotely to Logz.io, the end goal should just be to send remote logs to Logz.io and not to the console or a file.  This will have the best performance of minimizing I/O, as well as the benefit of not having to manage log rotations or disk space.

### High level steps
At a high level, here are the steps (review [Dockerfile](Dockerfile) for more specifics):
- Create/update your Maven *pom.xml* file to include the needed dependencies (review [pom.xml](pom.xml))
- Create/update your *log4j2.xml* file to include the *LogzioAppender* and desired properties (review [log4j2.xml](log4j2.xml))
- Package the necessary dependencies into your application & run

### Requirements
To run this example, you must:
- have the ability to clone this Docker repo (https://github.com/rentacenter/logzio.git)
- have installed [Docker](https://www.docker.com/)
- have the ability for Docker to pull images from [Docker Hub](https://hub.docker.com/)
- have a [logz.io](http://logz.io) account
- have the ability to communicate to *listener.logz.io* over secure *port 8071*

### Build the example
```shell
$ docker build -t logz/log4j2-example:1 .
```

### Run the example
```shell
$ docker run -e 'LOGZIO_TOKEN=<put your logz.io token here>' -e 'ENVIRONMENT=dev' logz/log4j2-example:1
```
