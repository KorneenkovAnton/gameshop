#STAGE 1: build
FROM openjdk:8-slim-buster as build
WORKDIR /epam/gameshop
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN sed -i "s|localhost|gameshop-db|g" src/main/resources/info.properties
RUN ./mvnw install -DskipTests

#STAGE 2: deploy
FROM tomcat:9-jdk8-openjdk-slim-buster
COPY --from=build /epam/gameshop/target/GameShop.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080

CMD ["catalina.sh", "run"]