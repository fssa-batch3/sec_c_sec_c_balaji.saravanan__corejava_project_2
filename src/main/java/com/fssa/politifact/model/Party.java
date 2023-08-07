package com.fssa.politifact.model;

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
