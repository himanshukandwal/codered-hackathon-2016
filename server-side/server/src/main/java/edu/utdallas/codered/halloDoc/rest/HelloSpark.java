package edu.utdallas.codered.halloDoc.rest;

import static edu.utdallas.codered.halloDoc.utils.JsonUtil.json;
import static spark.Spark.get;
import static spark.Spark.post;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import edu.utdallas.codered.halloDoc.config.spark.Spark;
import edu.utdallas.codered.halloDoc.model.DBResultObject;
import edu.utdallas.codered.halloDoc.model.SymtompsRequest;
import edu.utdallas.codered.halloDoc.model.SymtompsResponse;
import edu.utdallas.codered.halloDoc.utils.MongoDBConnection;


/**
 * 
 * @author Hxkandwal
 *
 */
@Component
public class HelloSpark implements Spark {

	@Autowired
	private MongoDBConnection dbConnection;
	
    @Override
    public void register() {
        get("/hello", (req, res) -> "hello world");
        post("/identifyDisease", "application/json", (req, res) -> {
			SymtompsRequest request = new Gson().fromJson(req.body(), SymtompsRequest.class);
			HashMap<String, Integer> diseaseFrequencyMap = new HashMap<>();
			HashMap<String, String[]> diseaseTreatmentMap = new HashMap<>();
			
			String disease = null;
			
			for (String symptoms : request.getSymptoms()) {
				DBCursor cursor = dbConnection.getHealthCareCollection().find(new BasicDBObject("$text", new BasicDBObject("$search", "\"" +symptoms + "\"")));
				
				while(cursor.hasNext()) {
					DBResultObject dbResultObject = new Gson().fromJson(cursor.next().toString(), DBResultObject.class);
					String localDisease = dbResultObject.getDisease();
					
					if (diseaseFrequencyMap.containsKey(localDisease))
						diseaseFrequencyMap.put(localDisease, diseaseFrequencyMap.get(localDisease) + 1);
					else
						diseaseFrequencyMap.put(localDisease, 1);
					
					diseaseTreatmentMap.put(localDisease, dbResultObject.getTreatment());
				}
				
				if (diseaseFrequencyMap.size() > 0) {
					int maxFreq = Collections.max(diseaseFrequencyMap.values());
				
					for (Map.Entry<String, Integer> diseaseFrequencyMapEntry : diseaseFrequencyMap.entrySet())
						if (diseaseFrequencyMapEntry.getValue() == maxFreq) 
							disease = diseaseFrequencyMapEntry.getKey();
					
				}
			}
			
			return (disease == null ? new SymtompsResponse() : new SymtompsResponse(disease, diseaseTreatmentMap.get(disease)));
		}, json());
    }
    
}
