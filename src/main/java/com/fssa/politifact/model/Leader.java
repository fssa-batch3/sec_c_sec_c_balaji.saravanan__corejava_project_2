/**
 * 
 */
package com.fssa.politifact.model;

import com.fssa.politifact.enums.Position;
import com.fssa.politifact.enums.Verify;

/**
 * @author BalajiSaravanan
 * 
/*
 * The Leader class is a model object that stores details about a Leader.
 * It includes the following attributes:
 *  id: An integer representing the unique ID of the Leader.
 *  Position: position is a enum class it's a constant.
 *  party  Name: A enum representing the name of the party to this also forien key of this table.
 *  constituency Name: A String representing the name of the leader.
 *  Occupation: An String representing the occupation of the leader.
 *  Experience : A double representing the experience of the leader model object.
 *  Descriptions: A String representing the descriptions of the leader table the description have five types of description.
 *  Image url: String tepresenting the image url of the leader.
 */
 
public class Leader {

	int id;

	private String name;

	private Position position;

	private String partyName;

	private double experience;

	private String occupation;

	private String counstuencyName;    

	private String descriptionOfBirth;

	private String descriptionOfEducation;

	private String descriptionOfPastWorkExperience;

	private String descritionOfpolitics;

	private String descriptionOffamily;

	private String descriptionOfIncome;

	private String imageUrl;
	
	private Verify verify;
	

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

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyname) {
		this.partyName = partyname;
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

	public String getCounstuencyName() {
		return counstuencyName;
	}

	public void setCounstuencyName(String counstuencyName) {
		this.counstuencyName = counstuencyName;
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
	
	public Verify getVerify() { 
		return verify;
	}

	public void setVerify(String verify) { 
		this.verify = Verify.valueOf(verify);
	}

	@Override
	public String toString() {
		return "Leader [id=" + id + ", name=" + name + ", position=" + position + ", partyName=" + partyName
				+ ", experience=" + experience + ", occupation=" + occupation + ", counstuencyName=" + counstuencyName
				+ ", descriptionOfBirth=" + descriptionOfBirth + ", descriptionOfEducation=" + descriptionOfEducation
				+ ", descriptionOfPastWorkExperience=" + descriptionOfPastWorkExperience + ", descritionOfpolitics="
				+ descritionOfpolitics + ", descriptionOffamily=" + descriptionOffamily + ", descriptionOfIncome="
				+ descriptionOfIncome + ", imageUrl=" + imageUrl + ", verify=" + verify + "]";
	}

}
