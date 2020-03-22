docker-assemble:
	docker run --rm       \
	-v ${HOME}/.m2:/root/.m2        \
	-v ${HOME}/.gradle:/root/.gradle \
	-v ${PWD}:/home/gradle/project      \
	-w /home/gradle/project     \
    openjdk:11-slim ./gradlew assemble $(ARG)

docker-unit-test:
	docker run --rm       \
	-v ${HOME}/.m2:/root/.m2        \
	-v ${HOME}/.gradle:/root/.gradle \
	-v ${PWD}:/home/gradle/project      \
	-w /home/gradle/project     \
	openjdk:11-slim ./gradlew test $(ARG)

docker-build-image:
	docker build -t coronavirus-api:latest -f Dockerfile .

docker-run: docker-assemble docker-build-image
	docker container run 							\
		-p 8080:8080 \
		-p 5005:5005 \
		-e JAVA_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=n"	\
		coronavirus-api
