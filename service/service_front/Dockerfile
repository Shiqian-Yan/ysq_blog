FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/service_front.jar service_front.jar
ENTRYPOINT ["java","-jar","/service_front.jar", "&"]
