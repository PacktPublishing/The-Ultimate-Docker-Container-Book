docker container run --rm \ 
    --name builder \ 
    -v /var/run/docker.sock:/var/run/docker.sock \ 
    -v "$PWD":/usr/src/app \ 
    -e HUB_USER=gnschenker \ 
    -e HUB_PWD=<password> \ 
    -e REPOSITORY=ch08-library \ 
    -e TAG=1.0 \ 
    builder