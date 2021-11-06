FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/*.jar /app/app.jar
COPY wait-for-it.sh /app/wait-for-it.sh
RUN chmod +x /app/wait-for-it.sh
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]

