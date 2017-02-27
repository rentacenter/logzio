#!/bin/bash

function usage() {
  if [ -n "$1" ]; then
    echo "$1" >&2
    echo "" >&2
  fi

  echo "Usage: docker run -e 'LOGZIO_TOKEN=<put your logz.io token here>' -e 'ENVIRONMENT=dev' logz/rsyslog-tls-example:1 [priority message]" >&2
  echo "Where: priority is a valid syslog priority <facility>.<level> (e.g. user.notice, security.warn, mail.info)" >&2
  echo "       message is the log message itself" >&2
  echo "" >&2
}

if [ -z "${LOGZIO_TOKEN}" ]; then
  usage "Missing LOGZIO_TOKEN environment variable"
  exit 1
fi

if [ -z "${ENVIRONMENT}" ]; then
  usage "Missing ENVIRONMENT environment variable"
  exit 1
fi

sed -i "s/__LOGZIO_TOKEN__/${LOGZIO_TOKEN}/g" /etc/rsyslog.d/21-logzio.conf
sed -i "s/__ENVIRONMENT__/${ENVIRONMENT}/g" /etc/rsyslog.d/21-logzio.conf

/sbin/rsyslogd -c5

if [ -n "$2" ]; then
  logger -s -p "$1" "$2"
else
  usage

  logger -s -p user.info "Testing logz.io!"
  logger -s -p user.warning "Winter is coming"

  logger -s -p user.err -t "Fatal" "This line has a fatal error"
fi

sleep 5
