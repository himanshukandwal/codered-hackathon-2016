package edu.utdallas.codered.halloDoc.utils;

import java.net.UnknownHostException;

import org.springframework.stereotype.Component;

import com.mongodb.DBCollection;
import com.mongodb.Mongo;

@Component
public class MongoDBConnection {

	private Mongo mongo;

	public DBCollection getHealthCareCollection() throws UnknownHostException {
		if (mongo == null) {
			mongo = new Mongo("localhost");
		}
		
		return mongo.getDB("local").getCollection("healthcare-big");
	}
	
	
}
