FROM golang:alpine 
WORKDIR /app 
ADD . /app 
RUN go env -w GO111MODULE=off
RUN cd /app && go build -o goapp 
ENTRYPOINT ./goapp