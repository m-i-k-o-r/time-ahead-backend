FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=*.jar
WORKDIR /app
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
