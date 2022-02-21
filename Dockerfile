FROM openjdk:11-jdk
VOLUME /tmp
ARG JAR_FILE=target/*.jar
ENV KEY abc
ENV PROFILE local
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", \
"-Dspring.profiles.active=${PROFILE}", \
"-Dazure.keyvault.client-key=${KEY}", \
"-jar", \
"/app.jar"]