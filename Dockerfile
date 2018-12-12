FROM jboss/wildfly:13.0.0.Final
MAINTAINER "jjdz5-magicy"
COPY console-app/target/magicy-app.jar /opt/jboss/wildfly/standalone/deployments
COPY web-app/target/jjdz5-magicy.war /opt/jboss/wildfly/standalone/deployments
RUN /opt/jboss/wildfly/bin/add-user.sh magik jjdz5magicy --silent
CMD ["echo Waiting 5 seconds"]
CMD ["sleep", "5"]
CMD ["echo Starting Wildfly"]
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]