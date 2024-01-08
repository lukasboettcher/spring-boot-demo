FROM gradle:alpine as build
COPY . .
RUN gradle build

FROM openjdk:17-alpine as main
USER 1000
COPY --from=build --chown=10000:10000 /home/gradle/build/libs/demo-1.0.0.jar /usr/src/app/app.jar
WORKDIR /usr/src/app
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]