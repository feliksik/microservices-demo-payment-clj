FROM clojure:latest

WORKDIR /opt/code

COPY src /opt/code/src
COPY project.clj /opt/code/project.clj

RUN lein uberjar

CMD [ "java", "-jar", "/opt/code/target/foobar-0.1.0-SNAPSHOT-standalone.jar"]
