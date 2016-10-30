package edu.utdallas.codered.halloDoc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DBResultObject {

	private UserDetails user_details;
	private String[] symptoms;
	private String disease;
	private String[] treatment;
	private String[] medicines;

	public DBObject randomClone() {
		
		DBObject dbResultObject = new BasicDBObject();
		int randomLength = new Random().nextInt(symptoms.length);
		List<String> randomSymptoms = new ArrayList<>();
		
		for (int idx = 0; idx < randomLength; idx ++)
			randomSymptoms.add(symptoms [idx]);
		
		Collections.shuffle(randomSymptoms);
		
		dbResultObject.put("user_details", UserDetails.getRandomDetails());
		dbResultObject.put("symptoms" , randomSymptoms.toArray(new String[0]));
		dbResultObject.put("disease", disease);
		dbResultObject.put("treatment", treatment);
		dbResultObject.put("medicines", medicines);
		
		return dbResultObject;
	}
	
	public String[] getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String[] symptoms) {
		this.symptoms = symptoms;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String[] getTreatment() {
		return treatment;
	}

	public void setTreatment(String[] treatment) {
		this.treatment = treatment;
	}

	public String[] getMedicines() {
		return medicines;
	}

	public void setMedicines(String[] medicines) {
		this.medicines = medicines;
	}
	
}