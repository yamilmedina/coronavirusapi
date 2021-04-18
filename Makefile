GCP_GOOGLE_APPLICATION_CREDENTIALS:=${HOME}/.config/gcloud/application_default_credentials.json
GCP_PROJECT_ID:=$(shell cat $(GCP_GOOGLE_APPLICATION_CREDENTIALS) | grep project_id | cut -d ":" -f 2 | tr -d '\", ')

docker-assemble:
	docker run --rm       \
	-v ${HOME}/.m2:/root/.m2        \
	-v ${HOME}/.gradle:/root/.gradle \
	-v ${PWD}:/home/gradle/project      \
	-w /home/gradle/project     \
    openjdk:11-slim ./gradlew clean assemble $(ARG)

docker-unit-test:
	docker run --rm       \
	-v ${HOME}/.m2:/root/.m2        \
	-v ${HOME}/.gradle:/root/.gradle \
	-v ${PWD}:/home/gradle/project      \
	-w /home/gradle/project     \
	openjdk:11-slim ./gradlew test $(ARG)

docker-build-image:
	docker build -t coronavirus-api:latest -f Dockerfile .

docker-run-dev: docker-assemble docker-build-image
	docker container run 							\
		-p 8080:8080 \
		-p 5005:5005 \
		-v $(GCP_GOOGLE_APPLICATION_CREDENTIALS):/home/.config/gcloud/adc.json 	\
		-e GOOGLE_APPLICATION_CREDENTIALS="/home/.config/gcloud/adc.json"	\
		-e PROJECT_ID="$(GCP_PROJECT_ID)"	\
		-e SPRING_PROFILES_ACTIVE="dev" 	\
		-e JAVA_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=n"	\
		coronavirus-api

gae-deploy:
	./gradlew appengineDeploy

gae-deploy-crons:
	./gradlew appengineDeployCron


