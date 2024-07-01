
-include .env

clean:
	mvn clean

build: clean
	mvn install -Dmaven.test.skip

debug: build
	java --enable-preview -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 ./target/product-service-0.0.1-SNAPSHOT.jar
