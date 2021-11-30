# Build image
FROM bellsoft/liberica-openjdk-alpine:16.0.2-7 AS builder
USER root
WORKDIR /app
COPY . .
RUN apk add maven
RUN mvn -B install -Dmaven.test.skip
RUN cp $(find /app/target/ -name 'organization-srv-*.jar') /app/target/organization-srv.jar

# Main app image
FROM bellsoft/liberica-openjre-alpine:16.0.2-7
EXPOSE 8080/tcp
WORKDIR /app
COPY --from=builder /app/target/organization-srv.jar /app/organization-srv.jar
ENTRYPOINT ["java", "-jar", "/app/organization-srv.jar"]


