
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/artgallery-0.0.1-SNAPSHOT.jar /app/artgallery.jar

CMD ["java", "-jar", "artgallery.jar"]
