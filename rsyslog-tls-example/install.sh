#!/bin/bash
yum -y install rsyslog-gnutls
mkdir -p -v /var/spool/rsyslog
cp AddTrustExternalCARoot.crt /etc/ssl/certs/
cp 21-logzio.conf /etc/rsyslog.d/
service rsyslog restart
