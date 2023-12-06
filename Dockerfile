FROM gradle:8.4-jdk-alpine AS Build

MAINTAINER  Eliud Gateri <egateri@gmail.com>

USER root

WORKDIR /usr/app/

COPY . .

RUN ./gradlew build


FROM amazoncorretto:21-alpine-jdk

WORKDIR /

COPY --from=Build /usr/app/build/libs/*.jar /app.jar

ENV TZ=Africa/Nairobi

EXPOSE 8080

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]