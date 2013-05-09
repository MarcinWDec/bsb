package bsbapp.model.enums;

public enum GenderE {

	MALE("Male"), FEMALE("Female");

	private final String label;

	private GenderE(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
