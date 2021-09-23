FROM maven:3.8.2-openjdk-16
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN mvn package
CMD ["java", "-jar", "target/ShopServer-1.0-SNAPSHOT.jar"]