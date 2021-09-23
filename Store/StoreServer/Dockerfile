FROM maven:3.8.2-openjdk-16
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
ENV SHOP_HOST=shopserver
ENV ACTIVEMQ_HOST=activemq
RUN mvn package
CMD ["java", "-jar", "target/StoreServer-0.0.1-SNAPSHOT.jar"]