package com.fssa.politifact.model;

public class Election {
	
    private int id;
    private int electionYear;
    private ElectionTypes electionType;
    private int leaderId;
    
    
	public Election(int id, int electionYear, ElectionTypes electionType, int leaderId) {
		
		this.id = id;
		this.electionYear = electionYear;
		this.electionType = electionType;
		this.leaderId = leaderId;
	} 

	public int getId() {
		
		return id;
	}

	public int getElectionYear() {
		
		return electionYear;
		
	}


	public ElectionTypes getElectionType() {
		return electionType;
	}

	public int getLeaderId() {
		return leaderId;
	}   
}
