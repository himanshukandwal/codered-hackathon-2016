from flask import Flask
from flask import render_template
from pymongo import MongoClient
import json
from bson import json_util
from bson.json_util import dumps

app = Flask(__name__)

MONGODB_HOST = 'localhost'
MONGODB_PORT = 27017
DBS_NAME = 'local'
COLLECTION_NAME = 'healthcare-big'
FIELDS = {'': True, 'resource_type': True, 'poverty_level': True, 'date_posted': True, 'total_donations': True, '_id': False}

@app.route("/")
def index():
    return "hello world"

@app.route("/healthcare/age/<agegroup>")
def healthcare_age(agegroup):
    connection = MongoClient(MONGODB_HOST, MONGODB_PORT)
    collection = connection[DBS_NAME][COLLECTION_NAME]
    records = collection.find({ 'user_details.age_group' : agegroup })
    json_records = []
    count = 0
    for record in records:
        json_records.append(record)
        count += 1
    json_records = json.dumps(json_records, default=json_util.default)
    connection.close()
    return "" + str(count)

@app.route("/healthcare/gender/<gender>")
def healthcare_gender(gender):
    connection = MongoClient(MONGODB_HOST, MONGODB_PORT)
    collection = connection[DBS_NAME][COLLECTION_NAME]
    records = collection.find({ 'user_details.gender' : gender })
    json_records = []
    count = 0
    for record in records:
        json_records.append(record)
        count += 1
    json_records = json.dumps(json_records, default=json_util.default)
    connection.close()
    return "" + str(count)

@app.route("/healthcare/disease/<disease>")
def healthcare_disease(disease):
    connection = MongoClient(MONGODB_HOST, MONGODB_PORT)
    collection = connection[DBS_NAME][COLLECTION_NAME]
    records = collection.find({ 'disease' : disease })
    json_records = []
    count = 0
    for record in records:
        json_records.append(record)
        count += 1
    json_records = json.dumps(json_records, default=json_util.default)
    connection.close()
    return "" + str(count)

if __name__ == "__main__":
    app.run(host='0.0.0.0',port=5000,debug=True)
