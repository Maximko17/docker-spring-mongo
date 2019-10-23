FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/spring-mongo-demo-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c "touch /app.jar"
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://db:27017/HotelDB","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
