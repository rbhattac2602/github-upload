FROM adoptopenjdk/openjdk8:latest
EXPOSE 5000
ADD target/greeting.jar greeting.jar
CMD ["java", "-Dfile.encoding=UTF-8", "-jar", "/greeting.jar"]
