FROM openjdk:8-jre-slim
MAINTAINER admin

RUN apt update && apt install -y curl libtcnative-1 && apt clean

ADD build/libs/resource-category.jar /

CMD ["java", "-Duser.timezone=Asia/Kolkata", "-jar" , "resource-category.jar"]
