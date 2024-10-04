# Executando um container desta imagem é possível rodar a aplicação sem a necessidade de instalar o Java ou o Maven na máquina local.
FROM maven:3.9.9-ibm-semeru-17-focal AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean install -DskipTests

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]