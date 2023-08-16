package com.fssa.politifact.model;

import com.fssa.politifact.enums.ElectionTypes;

/*
 * The election class is a model object that stores details about a election.
 * It includes the following attributes:
 * election id: An integer representing the unique ID of the election.
 * electionTypeName:election name is a enum class .
 * this also have a constuctor
 */

public class Election {

	private int id;
    private int electionYear;
    private ElectionTypes electionType;
    
	public Election(int id, int electionYear, ElectionTypes electionType) {
	
		this.id = id;
		this.electionYear = electionYear;
		this.electionType = electionType;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int eldctionid) {
		this.id = eldctionid;
	}          

	public int getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(int electionYear) {
		this.electionYear = electionYear;
	}

	public ElectionTypes getElectionType() {
		return electionType;
	} 

	public void setElectionType(String electionType) {
		this.electionType =ElectionTypes.valueOf(electionType);
	} 
	
	 @Override
		public String toString() {
			return "Election [id=" + id + ", electionYear=" + electionYear + ", electionType=" + electionType + "]";
		}
  

}
