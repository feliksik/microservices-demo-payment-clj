FROM clojure:latest

WORKDIR /opt/code

CMD ["lein", "ring", "server-headless"]
