# ---- build stage ---------------------------------------------------------
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml ./
RUN mvn -B -q dependency:go-offline
COPY src ./src
RUN mvn -B -q clean package -DskipTests

# ---- runtime stage: JRE only, non-root -----------------------------------
FROM eclipse-temurin:21-jre-alpine
RUN addgroup -S app && adduser -S app -G app
WORKDIR /app
COPY --from=build /app/target/loan-approval-api-0.0.1-SNAPSHOT.jar app.jar
USER app
ENV PORT=8080 \
    JAVA_OPTS="-XX:MaxRAMPercentage=75 -XX:+ExitOnOutOfMemoryError"
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=5s --start-period=90s --retries=3 \
  CMD wget -qO- "http://localhost:${PORT}/actuator/health" >/dev/null || exit 1
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]
