FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY .maven /app/.maven
COPY src /app/src
COPY pom.xml /app/pom.xml

RUN ./mvn/apache-maven-3.9.6/bin/mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/GymManagementWeb-0.0.1-SNAPSHOT.war"]