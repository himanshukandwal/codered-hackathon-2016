package edu.utdallas.codered.halloDoc.model;

public class SymtompsResponse {

	private String disease;
	private String[] treatments;
	
	public SymtompsResponse() {}
	
	public SymtompsResponse(String disease, String[] treatments) {
		this.disease = disease;
		this.treatments = treatments;
	}
	
	public String getDisease() {
		return disease;
	}
	
	public void setDisease(String disease) {
		this.disease = disease;
	}
	
	public String[] getTreatments() {
		return treatments;
	}

	public void setTreatments(String[] treatments) {
		this.treatments = treatments;
	}
	
}
