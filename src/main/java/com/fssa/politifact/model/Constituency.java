package com.fssa.politifact.model;

import com.fssa.politifact.enums.ElectionTypes;

/*
 * The Constituency class is a model object that stores details about a constituency.
 * It includes the following attributes:
 *  constituency id: An integer representing the unique ID of the constituency.
 *  consituency name: A string representing the name of the constituency.
 *  district Name: A string representing the name of the district to which the constituency belongs.
 *  constituency Number: An integer representing the number of the constituency.
 *  electionTypeName: An instance of the ElectionTypes enum representing the type of election associated with the constituency.
 */
public class Constituency {

	private int constituencyID;
	private String constituencyName;
	private String districtName;
	private int constituencyNumber;
	private int electionTypeName;
	
	public Constituency(String constituencyName, String districtName, int constituencyNumber,
			int electionTypeName) {
		
		this.constituencyName = constituencyName;
		this.districtName = districtName;
		this.constituencyNumber = constituencyNumber;
		this.electionTypeName = electionTypeName;
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



	public int getElectionTypeName() {
		return electionTypeName;
	}

	public void setElectionTypeName(int electionTypeName) {
		this.electionTypeName = electionTypeName;
	}

	@Override
	public String toString() {
		return "Constituency [constituencyID=" + constituencyID + ", constituencyName=" + constituencyName
				+ ", districtName=" + districtName + ", constituencyNumber=" + constituencyNumber
				+ ", electionTypeName=" + electionTypeName + "]";
	}



}
