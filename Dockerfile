FROM openjdk:11.0.9
COPY target/Algaworks-0.0.1-SNAPSHOT.jar /app.jar
COPY elastic-apm-agent-1.24.0.jar /apm-agent.jar
CMD ["/usr/bin/java","-javaagent:/apm-agent.jar", "-Delastic.apm.service_name=ibee-delivered -Delastic.apm.application_packages=ca.com.elastic -Delastic.apm.url_server=http://localhost:8200","-jar", "/app.jar"]