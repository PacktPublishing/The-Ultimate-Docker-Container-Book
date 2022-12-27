ARG BASE_IMAGE_VERSION=12.7-stretch 
FROM node:${BASE_IMAGE_VERSION} 
WORKDIR /app 
COPY packages.json . 
RUN npm install 
COPY . . 
CMD npm start 