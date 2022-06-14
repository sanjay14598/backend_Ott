From openjdk:8
copy ./target/ott-0.0.1-SNAPSHOT.jar ott-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","ott-0.0.1-SNAPSHOT.jar"]