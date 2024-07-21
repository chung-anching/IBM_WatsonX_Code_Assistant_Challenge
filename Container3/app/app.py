# Assisted by WCA@IBM
# Latest GenAI contribution: ibm/granite-20b-code-instruct-v2
import json
from flask import Flask, jsonify, request

app = Flask(__name__)

# @app.route('/api/v1/read', methods=['GET'])
# def read_json():
#     with open('data.json') as f:
#         data = json.load(f)
#     return data

@app.route('/api/v1/read', methods=['GET'])
def read_json():
    with open('data.json') as f:
        data = json.load(f)
    return jsonify(data)  # Convert dictionary to JSON response


@app.after_request
def after_request(response):
    response.headers.add('Access-Control-Allow-Origin', '*')
    response.headers.add('Access-Control-Allow-Headers', 'Content-Type,Authorization')
    response.headers.add('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE')
    return response

if __name__ == '__main__':
    app.run(host='0.0.0.0')

