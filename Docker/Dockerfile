FROM local-docker-registry-base.artifactory.colaboracionsimba.net:8081/openjdk:rhel9.3-java21-1.0.0
LABEL maintainer="CoEMicros@gruposalinas.com.mx"
ENV TZ=GMT+6
ENV proyect_name="CoE Micros from docker env"

WORKDIR /opt
USER root


COPY target/ts-demo-base-v1-0.0.1.jar /opt/ts-demo-base-v1-0.0.1.jar


EXPOSE 8081

ENTRYPOINT ["java","-jar", "/opt/ts-demo-base-v1-0.0.1.jar"]
