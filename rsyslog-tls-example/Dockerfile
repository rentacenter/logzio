# Build from CentOS image
FROM centos:6
MAINTAINER Troy Washburn <troy.washburn@rentacenter.com>

RUN yum -y install rsyslog-gnutls \
  && mkdir -p -v /var/spool/rsyslog \
  && mkdir -p -v /usr/local/bin

COPY AddTrustExternalCARoot.crt /etc/ssl/certs/
COPY 21-logzio.conf /etc/rsyslog.d/
COPY run.sh /usr/local/bin/

ENTRYPOINT ["/usr/local/bin/run.sh"]
