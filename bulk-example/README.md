# Web Services/Bulk example
This example shows how to use the [Logz.io HTTPS Bulk](https://app.logz.io/#/dashboard/data-sources/Bulk-HTTPS) interface as a simple web service endpoint to centralize your logs.  (While this example is written in Java, this interface is platform agnostic.)

While this example shows a very simple approach to sending JSON logs to a web service endpoint, it does not handle multiple items that should be considered (i.e. non-blocking threading, asynchronous approach, automatic retries, persistence).  If you have a Java application and can support log4j, I highly recommend taking a look at either the [log4j example](../log4j-example) or [log4j2 example](../log4j2-example).  If you can't support log4j, the preferred method for Java applications is to utilize the [Logz.io Java Sender](../java-sender-example) library directly; it automatically handles the above considerations.

### High level steps
At a high level, here are the steps (reference [Logz.io Bulk format](https://app.logz.io/#/dashboard/data-sources/Bulk-HTTPS))
- Construct Logz.io endpoint URL (`https://listener.logz.io:8071/?token=<token>&type=<type>`)
- Create JSON document with required (**environment**, **application**) and any optional fields (e.g. **team**, **project**, **message**) (review [Source Code](src/main/java/com/rentacenter/examples/LogzioBulkExample.java))
- Post log to Logz.io

### Build the example
```shell
$ docker build -t logz/bulk-example:1 .
```

### Run the example
```shell
$ docker run -e 'LOGZIO_TOKEN=<put your logz.io token here>' -e 'ENVIRONMENT=dev' logz/bulk-example:1
```
