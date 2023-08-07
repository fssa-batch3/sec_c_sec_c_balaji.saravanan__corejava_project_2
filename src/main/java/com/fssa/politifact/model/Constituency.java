package com.fssa.politifact.model;

public class Constituency {
	
	private int constituencyID;
	private String constituencyName;
	private String districtName;
	private int constituencyNumber;
	private int electionTypeId;
	
	public Constituency(String constituencyName, String districtName, int constituencyNumber,
			int electionTypeId) {

		this.constituencyName = constituencyName;
		this.districtName = districtName;
		this.constituencyNumber = constituencyNumber;
		this.electionTypeId = electionTypeId;  
	} 

	public int getConstituencyID() {
		return constituencyID;
	}
	
	public void setConstituencyID(int constituencyID) {
		this.constituencyID = constituencyID;
	}

	public String getConstituencyName() {
		return constituencyName;
	}
	
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	
	public String getDistrictName() {
		return districtName;
	}
	
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	public int getConstituencyNumber() {
		return constituencyNumber;
	}
	
	public void setConstituencyNumber(int constituencyNumber) {
		this.constituencyNumber = constituencyNumber;
	}
	
	public int getElectionTypeId() {
		return electionTypeId;
	}

	public void setElectionTypeId(int electionTypeId) {
		this.electionTypeId = electionTypeId;
	}

	@Override
	public String toString() {
		return "Constituency [constituencyID=" + constituencyID + ", constituencyName=" + constituencyName
				+ ", districtName=" + districtName + ", constituencyNumber=" + constituencyNumber + ", electionTypeId="
				+ electionTypeId + "]";
	}
	

}


