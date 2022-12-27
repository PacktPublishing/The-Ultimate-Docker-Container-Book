from flask import Flask 
app = Flask(__name__) 
@app.route("/") 
def hello(): 
    return "Hello World!" 
from flask import jsonify 
@app.route("/colors") 
def colors(): 
    return jsonify(["red", "green", "blue"]) 
if __name__ == "__main__": 
    app.run(host="0.0.0.0", port=5000)