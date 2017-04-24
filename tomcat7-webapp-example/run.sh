#!/bin/bash

# start up Tomcat in background
catalina.sh start

# wait for "started" message
echo "waiting for tomcat startup"
while [ true ]; do
  grep -q "Server startup in" $CATALINA_HOME/logs/catalina.out >/dev/null 2>&1
  [ $? -eq 0 ] && break
  sleep 0.5
done

# access web-application
for i in {1..1200}; do
  echo "$i: accessing example web-application"
  curl -vvv http://localhost:8080/spring-webapp-example/ >/dev/null 2>&1
  sleep 1
done

# shutdown tomcat
catalina.sh stop

# wait for tomcat shutdown
echo "waiting for graceful shutdown"
while [ true ]; do
  grep -q "Destroying ProtocolHandler" $CATALINA_HOME/logs/catalina.out >/dev/null 2>&1
  [ $? -eq 0 ] && break
  sleep 0.5
done
