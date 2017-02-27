# rsyslog over TLS example
This example shows how to use the [Logz.io rsyslog over TLS](https://app.logz.io/#/dashboard/data-sources/rsyslog-overTLS) to centralize your Linux system logs.

### High level steps
At a high level, here are the steps (review [Dockerfile](Dockerfile) for more specifics):
- Install *rsyslog-gnutls* package
- Create */var/spool/rsyslog* directory
- Install certificate (*AddTrustExternalCARoot.crt*) into */etc/ssl/certs*
- Install rsyslog configuration file (*21-logzio.conf*) into */etc/rsyslog.d*
  - Update the **environment** and **application** fields in the logformat
  - You may want to remove or update the **team** and **project** fields
  - If you need additional fields, add them as well in similar format
- Restart *rsyslog*

### Build the example
```shell
$ docker build -t logz/rsyslog-tls-example:1 .
```

### Run the example
```shell
$ docker run -e 'LOGZIO_TOKEN=<put your logz.io token here>' -e 'ENVIRONMENT=dev' logz/rsyslog-tls-example:1
$ docker run -e 'LOGZIO_TOKEN=<put your logz.io token here>' -e 'ENVIRONMENT=dev' logz/rsyslog-tls-example:1 user.notice "This is my log message"
```
