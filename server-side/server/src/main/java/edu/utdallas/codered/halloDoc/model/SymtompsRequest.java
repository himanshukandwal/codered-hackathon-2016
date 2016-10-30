package edu.utdallas.codered.halloDoc.model;

public class SymtompsRequest {

	private UserDetails user_details;
	private String[] symptoms;

	public UserDetails getUser_details() {
		return user_details;
	}
	
	public void setUser_details(UserDetails user_details) {
		this.user_details = user_details;
	}
	
	public String[] getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String[] symptoms) {
		this.symptoms = symptoms;
	}

}
