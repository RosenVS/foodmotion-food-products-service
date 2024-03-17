#FROM eclipse-temurin:17-jdk-jammy as builder
#WORKDIR /opt/app
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#COPY ./src ./src
#RUN ./mvnw clean install
#
#FROM eclipse-temurin:17-jre-jammy
#WORKDIR /opt/app
#COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
#ENTRYPOINT ["java", "-jar", "/opt/app/*.jar" ]
## docker build -t individual-service-registry .
## docker run -p 8761:8761 -d individual-service-registry:latest

FROM eclipse-temurin:17-jdk-jammy as builder

# Set JAVA_HOME for builder stage
ENV JAVA_HOME /usr/local/openjdk-17

WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install

FROM eclipse-temurin:17-jre-jammy

# Set JAVA_HOME for runtime stage
ENV JAVA_HOME /usr/local/openjdk-17

WORKDIR /opt/app
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar"]

