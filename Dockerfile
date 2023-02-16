#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
COPY pom.xml /app/
COPY src /app/src
RUN mvn -f /app/pom.xml clean package -DskipTests


FROM openjdk:17
COPY --from=build /app/target/desafio-0.0.1-SNAPSHOT.jar /app/desafio.jar
ENTRYPOINT ["java","-Dserver.port=$PORT","-Xmx268M","-Xss512K","-XX:CICompilerCount=2","-Dfile.encoding=UTF-8","-XX:+UseContainerSupport","-Djava.security.egd=file:/dev/./urandom","-Xlog:gc","-jar","/app/desafio.jar"]