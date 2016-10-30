package edu.utdallas.codered.halloDoc.model;

public class DBResultObject {

	private String[] symptoms;
	private String disease;
	private String[] treatment;
	private String[] medicines;

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
