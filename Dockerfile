FROM amazoncorretto:21-alpine
ADD target/api.jar api.jar
ENTRYPOINT ["java","-jar","/api.jar"]