/**
 * 
 */
package com.fssa.politifact.model;

/**
 * @author BalajiSaravanan
 * 
 *         Model object for representing the all leaders
 *
 */
public class Leaders {
	
	int id;

	private String name;

	private Position position;

	private int partyId;

	private double experience;

	private String occupation;

	private int counstuencyId;

	private String descriptionOfBirth;

	private String descriptionOfEducation;

	private String descriptionOfPastWorkExperience;

	private String descritionOfpolitics;

	private String descriptionOffamily;

	private String descriptionOfIncome;

	private String imageUrl;
 
	private int affidavitId;

	public Leaders(String name, Position position, int partyId, double experience, String occupation, int counstuencyId,
			String descriptionOfBirth, String descriptionOfEducation, String descriptionOfPastWorkExperience,
			String descritionOfpolitics, String descriptionOffamily, String descriptionOfIncome, String imageUrl,
			int affidavitId) {

		this.name = name;
		this.position = position;
		this.partyId = partyId;
		this.experience = experience; 
		this.occupation = occupation;
		this.counstuencyId = counstuencyId;
		this.descriptionOfBirth = descriptionOfBirth;
		this.descriptionOfEducation = descriptionOfEducation;
		this.descriptionOfPastWorkExperience = descriptionOfPastWorkExperience;
		this.descritionOfpolitics = descritionOfpolitics;
		this.descriptionOffamily = descriptionOffamily;
		this.descriptionOfIncome = descriptionOfIncome;
		this.imageUrl = imageUrl;
		this.affidavitId = affidavitId;
	}
	
	

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(String positionString) {
	    this.position = Position.valueOf(positionString);
	}

	public int getPartyId() {
		return partyId;
	}

	public void setPartyId(int partyId) {
		this.partyId = partyId;
	}

	public double getExperience() {
		return experience;
	}

	public void setExperience(double experience) {
		this.experience = experience;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public int getCounstuencyId() {
		return counstuencyId;
	}

	public void setCounstuencyId(int counstuencyId) {
		this.counstuencyId = counstuencyId;
	}

	public String getDescriptionOfBirth() {
		return descriptionOfBirth;
	} 

	public void setDescriptionOfBirth(String descriptionOfBirth) {
		this.descriptionOfBirth = descriptionOfBirth;
	}

	public String getDescriptionOfEducation() {
		return descriptionOfEducation;
	}

	public void setDescriptionOfEducation(String descriptionOfEducation) {
		this.descriptionOfEducation = descriptionOfEducation;
	} 

	public String getDescriptionOfPastWorkExperience() {
		return descriptionOfPastWorkExperience;
	}

	public void setDescriptionOfPastWorkExperience(String descriptionOfPastWorkExperience) {
		this.descriptionOfPastWorkExperience = descriptionOfPastWorkExperience;
	}

	public String getDescritionOfpolitics() {
		return descritionOfpolitics;
	}

	public void setDescritionOfpolitics(String descritionOfpolitics) {
		this.descritionOfpolitics = descritionOfpolitics;
	}

	public String getDescriptionOffamily() {
		return descriptionOffamily;
	}

	public void setDescriptionOffamily(String descriptionOffamily) {
		this.descriptionOffamily = descriptionOffamily;
	}

	public String getDescriptionOfIncome() {
		return descriptionOfIncome;
	}

	public void setDescriptionOfIncome(String descriptionOfIncome) {
		this.descriptionOfIncome = descriptionOfIncome;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getAffidavitId() {
		return affidavitId;
	}

	public void setAffidavitId(int affidavitId) {
		this.affidavitId = affidavitId;
	}

	@Override
	public String toString() { 
		return "Leaders [name=" + name + ", position=" + position + ", partyId=" + partyId + ", experience="
				+ experience + ", occupation=" + occupation + "counstuencyId=" + counstuencyId + ", descriptionOfBirth=" + descriptionOfBirth
				+ ", descriptionOfEducation=" + descriptionOfEducation + ", descriptionOfPastWorkExperience="
				+ descriptionOfPastWorkExperience + ", descritionOfpolitics=" + descritionOfpolitics
				+ ", descriptionOffamily=" + descriptionOffamily + ", descriptionOfIncome=" + descriptionOfIncome
				+ ", imageUrl=" + imageUrl + ", affidavitId=" + affidavitId + "]";
	}

}
