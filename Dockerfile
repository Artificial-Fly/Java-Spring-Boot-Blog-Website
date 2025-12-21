FROM openjdk:27-ea-trixie
ADD  target/blogWebsite.jar blogWebsite.jar
ENTRYPOINT ["java", "-jar", "/blogWebsite.jar"]