build:
	mkdir -p target/m2
	docker build -t ms-payment .

# run command for dev purposes, mounts local source and bins
run:
	docker run -ti -p 3000:3000 \
		-v $(shell pwd)/target/m2:/root/.m2 \
		-v $(shell pwd):/opt/code ms-payment lein ring server-headless
repl:
	docker run -ti \
		-v $(shell pwd)/target/m2:/root/.m2 \
		-v $(shell pwd):/opt/code ms-payment lein repl

build-in-docker:
	docker run --rm -ti \
		-v $(shell pwd)/target/m2:/root/.m2 \
		-v $(shell pwd):/opt/code ms-payment lein uberjar

runimage:
	docker run -ti -p 3000:3000 ms-payment
