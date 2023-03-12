from flask import Flask, escape, request
import json

app = Flask(__name__)

@app.route('/')
def home():
    return "eShop Sample Application"

@app.route('/catalog')
def catalog():
    list = [
        { "id": 1, "name": "Mountanbike Driftwood 24\"", "unitPrice": 199 },
        { "id": 2, "name": "Tribal 100 Flat Bar Cycle Touring Road Bike", "unitPrice": 300 },
        { "id": 3, "name": "Siech Cycles Bike (58 cm)", "unitPrice": 459 }
    ]
    return json.dumps(list)

@app.route('/checkout')
def checkout():
    return "Starting checkout of your shopping cart..."

# Uncomment the following 2 lines to have the app listen on 0.0.0.0:5000 
if __name__ == '__main__':
    app.run(port=5000)