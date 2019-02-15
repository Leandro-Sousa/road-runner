FROM debian:latest

RUN apt-get update
RUN apt-get install default-jre -y
RUN mkdir /app

WORKDIR /app

COPY ./build/libs/road-runner-project-1.0-SNAPSHOT.jar .

EXPOSE 80/tcp

ENTRYPOINT ["java", "-jar", "road-runner-project-1.0-SNAPSHOT.jar"]
