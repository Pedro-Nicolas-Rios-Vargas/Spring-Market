FROM gradle:latest as builder

LABEL authors="ordep"

COPY [".", "/home/gradle/project/"]

WORKDIR /home/gradle/project

RUN ["./gradlew", "bootJar"]


FROM amazoncorretto:21

COPY --from=builder ["/home/gradle/project/build/libs/market-1.0.jar", "/usr/src/"]

WORKDIR /usr/src

EXPOSE 80

CMD ["java", "-jar", "-Dspring.profiles.active=pdn", "./market-1.0.jar"]
