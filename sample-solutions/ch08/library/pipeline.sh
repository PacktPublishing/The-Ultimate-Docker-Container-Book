#! /bin/bash 

# *** Sample script to build, test & push         ***
# *** containerized Java/Spring Boot applications ***

# build the Docker image 
docker image build -t $HUB_USER/$REPOSITORY:$TAG . 
# Run all unit tests 
docker container run $HUB_USER/$REPOSITORY:$TAG ./mvnw test 
# Login to Docker Hub 
docker login -u $HUB_USER -p $HUB_PWD 
# Push the image to Docker Hub 
docker image push $HUB_USER/$REPOSITORY:$TAG 