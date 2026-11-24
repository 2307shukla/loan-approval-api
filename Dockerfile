# ---- build stage ---------------------------------------------------------
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml maven-settings.xml ./
# repo.maven.apache.org rate-limits (HTTP 429) Render's shared build IPs, which
# fails the whole deploy while resolving the parent POM. maven-settings.xml
# mirrors Maven Central through Google's GCS-backed mirror (same artifacts, no
# such limit); the retry loops cover any other transient failure and still
# fail the build with exit 1 if every attempt fails.
RUN set -e; \
    for i in 1 2 3 4 5; do \
      mvn -B -s maven-settings.xml dependency:go-offline && exit 0; \
      echo "Dependency resolution failed, retrying in 20s ($i/5)..."; \
      sleep 20; \
    done; \
    echo "Dependency resolution failed after 5 attempts"; exit 1
COPY src ./src
RUN set -e; \
    for i in 1 2 3 4 5; do \
      mvn -B -s maven-settings.xml clean package -DskipTests && exit 0; \
      echo "Build failed, retrying in 20s ($i/5)..."; \
      sleep 20; \
    done; \
    echo "Build failed after 5 attempts"; exit 1

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
