FROM node:12.10-alpine
RUN mkdir /app
WORKDIR /app
COPY package.json /app/
RUN npm install
COPY ./public /app/public
COPY ./src /app/src
EXPOSE 3000
CMD node src/server.js