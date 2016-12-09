FROM clojure:latest

WORKDIR /opt/code
COPY project.clj /opt/code/project.clj
RUN lein install
COPY . /opt/code
RUN lein ring war

CMD ["lein", "ring", "server-headless"]
