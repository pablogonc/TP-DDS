FROM maven:3.8.6-openjdk-11 as builder
WORKDIR /project
ADD . /project/
RUN mvn package

# Second stage
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=builder /project/target/demo-0.0.1-SNAPSHOT.jar /app
COPY --from=builder /project/src/main/resources /app/src/main/resources

EXPOSE 5000

CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]