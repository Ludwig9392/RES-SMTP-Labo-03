#Docker MockMock SMTP server
# Based on alpine java
FROM java:openjdk-8-jre-alpine

# Add the jar of the MockMock server
ADD MockMock-1.4.0.one-jar.jar /home/

# Starting directory when we run the container
WORKDIR /home/

# Defining an entry point to use the docker run <cmd> like a parameter
ENTRYPOINT ["java", "-jar", "MockMock-1.4.0.one-jar.jar"]


