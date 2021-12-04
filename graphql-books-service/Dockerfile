FROM gradle:latest AS build

COPY --chown=gradle:gradle ./graphql-books-service /home/gradle/src
COPY --chown=gradle:gradle ../settings.gradle /home/gradle/src/settings.gradle
COPY --chown=gradle:gradle ../gradle.properties /home/gradle/src/gradle.properties

WORKDIR /home/gradle/src

RUN gradle clean build --daemon --stacktrace

FROM openjdk:17-jdk-oracle as prod

RUN mkdir /app
RUN mkdir /app/external

COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
COPY --from=build /home/gradle/src/build/resources /app/resources

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "-Dspring.profiles.active=prod", "/app/spring-boot-application.jar"]

EXPOSE 8000