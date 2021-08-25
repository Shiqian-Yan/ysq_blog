FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/service_osss.jar service_osss.jar
ENTRYPOINT ["java","-jar","/service_osss.jar", "&"]
