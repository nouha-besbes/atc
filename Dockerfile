FROM anapsix/alpine-java:8

ENTRYPOINT ["java","-jar","/home/runner/work/authentication/authentication/controller/target/controller-0.0.1-SNAPSHOT.jar"]