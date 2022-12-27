FROM nikolaik/python-nodejs:latest
RUN npm install -g nodemon
WORKDIR /app
COPY requirements.txt .
RUN pip install -r requirements.txt
COPY . .
CMD [ "nodemon", "--exec", "python", "main.py" ]