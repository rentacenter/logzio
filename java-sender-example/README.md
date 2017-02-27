# Java Sender example
This example shows how to use the [Logz.io Java Sender](https://github.com/logzio/logzio-java-sender) library directly to centralize your logs.

The benefit of using this library over the [bulk](../bulk-example) web service interface is that it automatically handles non-blocking, asynchronous, retry, and persistence features (and is fully supported by Logz.io).  However, if you can support log4j, I highly recommend taking a look at either the [log4j example](../log4j-example) or [log4j2 example](../log4j2-example).

### High level steps
At a high level, here are the steps (reference [Logz.io Java Sender](https://github.com/logzio/logzio-java-sender))
- Create the Logzio Java Sender with the desired arguments
- Create log message with required (**environment**, **application**) and any optional fields (e.g. **team**, **project**, **message**) (review [Source Code](src/main/java/com/rentacenter/examples/LogzioJavaSenderExample.java))
- Send log to Logz.io
- Shut down the sender at application exit (i.e. shutdown hook)

### Build the example
```shell
$ docker build -t logz/java-sender-example:1 .
```

### Run the example
```shell
$ docker run -e 'LOGZIO_TOKEN=<put your logz.io token here>' -e 'ENVIRONMENT=dev' logz/java-sender-example:1
```
