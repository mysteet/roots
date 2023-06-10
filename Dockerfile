FROM eclipse-temurin:17.0.7_7-jdk-alpine as builder
ARG JAR_FILE=/build/libs/*.jar
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM eclipse-temurin:17.0.7_7-jdk-alpine
VOLUME /main
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
ENTRYPOINT java $OPTS org.springframework.boot.loader.JarLauncher
