import logging

logger = logging.getLogger("Python demo app")
logger.setLevel(logging.WARN)
# create a console handler
ch = logging.StreamHandler()
# create a formatter and add it to the handlers
formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
ch.setFormatter(formatter)
logger.addHandler(ch)

from flask import Flask 
app = Flask(__name__) 
@app.route("/") 
def hello(): 
    logger.info("Accessing endpoint '/'")
    return "Hello World!" 

from flask import jsonify 
@app.route("/colors") 
def colors(): 
    logger.warning("Warning, you are accessing /colors")
    return jsonify(["red", "green", "blue"]) 

if __name__ == "__main__": 
    app.run(host="0.0.0.0", port=5000)