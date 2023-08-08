package com.fssa.politifact.model;

public class Affidavit {
	
	private int id;
	private int electionId;
	private int LeaderId;
	private String affidateUrl;
	
	public Affidavit(int id, int electionId, int leaderId, String affidateUrl) {
		
		this.id = id;
		this.electionId = electionId;
		LeaderId = leaderId;
		this.affidateUrl = affidateUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getElectionId() {
		return electionId;
	}

	public void setElectionId(int electionId) {
		this.electionId = electionId;
	}

	public int getLeaderId() {
		return LeaderId;
	}

	public void setLeaderId(int leaderId) {
		LeaderId = leaderId;
	}

	public String getAffidateUrl() {
		return affidateUrl;
	}

	public void setAffidateUrl(String affidateUrl) {
		this.affidateUrl = affidateUrl;
	}  

}
