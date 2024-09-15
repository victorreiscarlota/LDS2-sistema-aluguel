# # Usar uma imagem base com Java 17
# FROM eclipse-temurin:17-jdk-alpine

# # Definir o diretório de trabalho
# WORKDIR /app

# # Copiar o arquivo JAR da aplicação para o contêiner
# COPY target/*.jar app.jar

# # Expor a porta que a aplicação irá usar
# EXPOSE 8080

# # Comando para executar a aplicação
# ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:resolve
COPY src ./src
EXPOSE 8080
CMD ["./mvnw", "spring-boot:run"]

