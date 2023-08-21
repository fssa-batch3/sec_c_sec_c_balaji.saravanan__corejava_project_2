package com.fssa.politifact.model;

/*
 * the model class help to store the affidavit detils in the particular candidate
 * affidate is one to mani relation ship so this is a separate model object
 * affidate hava a id, election id (forien key), leader id (forien key) and affidavit url.
 * this have a one constuctor thai is initialize the values.
 * this class also have getter setter.
 */

public class Affidavit {

	private int id;
	private int electionId;
	private int leaderId;
	private String affidateUrl;
	
	
	public Affidavit(int electionId, int leaderId, String affidateUrl) {
		
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


	@Override
	public String toString() {
		return "Affidavit [id=" + id + ", electionId=" + electionId + ", leaderId=" + leaderId + ", affidateUrl="
				+ affidateUrl + "]";
	}
	
	
	

}
