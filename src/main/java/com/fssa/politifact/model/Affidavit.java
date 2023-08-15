package com.fssa.politifact.model;

public class Affidavit {

	private int id;
	private int electionId;
	private int leaderId;
	private String affidateUrl;
	
	
	public Affidavit(int id, int electionId, int leaderId, String affidateUrl) {
		
		this.id = id;
		this.electionId = electionId;
		this.leaderId = leaderId;
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
		return leaderId;
	}


	public void setLeaderId(int leaderId) {
		this.leaderId = leaderId;
	}


	public String getAffidateUrl() {
		return affidateUrl;
	}


	public void setAffidateUrl(String affidateUrl) {
		this.affidateUrl = affidateUrl;
	}
	

}
