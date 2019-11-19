FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/demo-tripatra.jar app.jar
ENTRYPOINT [ "java", "-Xmx128m", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar" ]
