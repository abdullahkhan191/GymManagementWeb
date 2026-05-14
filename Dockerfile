FROM eclipse-temurin:21-jdk-alpine

RUN apk add --no-cache maven

WORKDIR /app

COPY src /app/src
COPY pom.xml /app/pom.xml

RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/GymManagementWeb-0.0.1-SNAPSHOT.war"]