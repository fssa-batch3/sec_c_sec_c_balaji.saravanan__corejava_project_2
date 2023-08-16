package com.fssa.politifact.model;


/*
 * The Party class represents a political party and stores relevant information.
 * It contains the following attributes:
 *  partyId: An integer representing the unique ID of the party.
 *  partyName: A string representing the name of the party.
 *  partyImageUrl: A string representing the URL of the party's image/logo.
 */


public class Party {
	
	private int partyId;
	private String partyName;
	private String partyImageUrl;
	
	public Party( String partyName, String partyImageUrl) {
		
		this.partyName = partyName;
		this.partyImageUrl = partyImageUrl;
		
	} 

	public int getPartyId() {
		return partyId;
	}

	public void setPartyId(int partyId) {
		this.partyId = partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyImageUrl() {
		return partyImageUrl;
	}

	public void setPartyImageUrl(String partyImageUrl) {
		this.partyImageUrl = partyImageUrl;
	}

	@Override
	public String toString() {
		return "Party [partyId=" + partyId + ", partyName=" + partyName + ", partyImageUrl=" + partyImageUrl + "]";
	}

	
	
}
