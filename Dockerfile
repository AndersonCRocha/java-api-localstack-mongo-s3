FROM maven:3.8.1-adoptopenjdk-15

COPY . /var/www
WORKDIR /var/www

COPY . .

CMD mvn spring-boot:run -DskipTests

#COPY ./target/*.jar app.jar
#
#CMD java -jar app.jar