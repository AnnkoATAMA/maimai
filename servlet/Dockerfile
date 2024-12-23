FROM eclipse-temurin:21-jdk

RUN apt-get update && apt-get install -y unzip && rm -rf /var/lib/apt/lists/*

WORKDIR /servlet

COPY . .

RUN ./gradlew clean shadowJar --no-daemon

EXPOSE 8080

CMD ["java", "-jar", "build/libs/maimai-1.0-SNAPSHOT.jar"]
