# Estágio 1: Build da aplicação
FROM maven:3-openjdk-17 AS build-image

# Definir o diretório de trabalho para o build
WORKDIR /to-build-app

# Copiar o arquivo de configuração do Maven (pom.xml) para obter as dependências
COPY pom.xml ./

# Baixar as dependências do Maven (usar go-offline para cachear dependências)
RUN mvn dependency:go-offline

# Copiar o restante do código-fonte da aplicação
COPY src ./src

# Executar a build do pacote JAR, sem rodar os testes
RUN mvn package -DskipTests

# Estágio 2: Construção da imagem final
FROM eclipse-temurin:17-jre-alpine

# Definir o diretório de trabalho da aplicação
WORKDIR /app

# Copiar o pacote JAR gerado no estágio anterior para o diretório de trabalho da aplicação
COPY --from=build-image /to-build-app/target/*.jar /app/app.jar

# Expor a porta 8080 (a porta que a aplicação vai usar)
EXPOSE 8080

# Definir o comando de entrada para rodar a aplicação Java
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

