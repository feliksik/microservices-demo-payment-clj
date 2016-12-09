FROM clojure:latest

WORKDIR /opt/code
COPY project.clj /opt/code/project.clj
RUN lein install

CMD ["lein", "ring", "server-headless"]
