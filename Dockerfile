FROM openjdk:8
WORKDIR /app
#ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/postgres
#ENV SPRING_DATASOURCE_USERNAME=postgres
#ENV SPRING_DATASOURCE_PASSWORD=root
ADD target/bus-service-app.jar bus-service-app.jar
COPY target/bus-service-app.jar /app/bus-service-app.jar
COPY src/main/resources/application.properties /app/application.properties
EXPOSE 8080
CMD [ "java", "-jar", "/app/bus-service-app.jar" ]