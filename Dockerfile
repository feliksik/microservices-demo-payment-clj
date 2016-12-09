FROM clojure:latest

WORKDIR /opt/code
COPY project.clj /opt/code/project.clj
COPY . /opt/code
RUN lein ring war

CMD ["lein", "ring", "server-headless"]
