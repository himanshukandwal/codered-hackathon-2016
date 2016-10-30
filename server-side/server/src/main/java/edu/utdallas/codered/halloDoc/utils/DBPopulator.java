package edu.utdallas.codered.halloDoc.utils;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Random;

import com.google.gson.Gson;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

import edu.utdallas.codered.halloDoc.model.DBResultObject;

public class DBPopulator {

	private static Mongo mongo;
	
	public static DBCollection getHealthCareOriginalCollection() throws UnknownHostException {
		if (mongo == null) {
			mongo = new Mongo("localhost");
		}
		
		return mongo.getDB("local").getCollection("healthcare");
	}
	
	public static DBCollection getHealthCareCollection() throws UnknownHostException {
		if (mongo == null) {
			mongo = new Mongo("localhost");
		}
		
		return mongo.getDB("local").getCollection("healthcare-big");
	}
	
	private static Random getRandomizer() {
		return new Random();
	}
	
	public static void main1(String[] args) throws UnknownHostException {
		
		HashMap<Integer, DBResultObject> metaData = new HashMap<>();
		
		DBCursor cursor = getHealthCareOriginalCollection().find();
		int count = 0;
		while (cursor.hasNext()) {
			DBResultObject dbResultObject = new Gson().fromJson(cursor.next().toString(), DBResultObject.class);
			metaData.put(count ++, dbResultObject);
		}
		
		int documentsInserted = 0;
		while (documentsInserted ++ < 20000) {
			int index = getRandomizer().nextInt(metaData.size());
			DBResultObject dbResultObject = metaData.get(index);
			DBObject dbObject = dbResultObject.randomClone();
			
			getHealthCareCollection().insert(dbObject);
		}
		
	}
}
