build:
	mkdir -p target/m2
	docker build -t ms-payment .
run:
	docker run -ti -p 3000:3000 \
								 -v $(shell pwd)/target/m2:/root/.m2 \
								 -v $(shell pwd):/opt/code ms-payment
