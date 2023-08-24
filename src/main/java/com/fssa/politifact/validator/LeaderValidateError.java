package com.fssa.politifact.validator;

public class LeaderValidateError {

	private LeaderValidateError() {

	}
	/**
	 * this is consent message so we store.
	 */

	public static final String INVALID_LEADER_NULL = "Leader object cannot be null.";
	public static final String INVALID_NAME = "Leader name cannot be null.";
	public static final String INVALID_POSITION = "Leader's position cannot be null.";
	public static final String INVALID_PARTY_ID = "Party ID cannot be less than zero.";
	public static final String INVALID_EXPERIENCE = "Leader's experience cannot be null.";
	public static final String INVALID_OCCUPATION = "Leader's occupation cannot be null.";
	public static final String INVALID_STARTING_DATE = "Invalid starting date.";
	public static final String INVALID_ENDING_DATE = "Invalid ending date.";
	public static final String INVALID_CONSTITUENCY_NAME = "Constituency name cannot be null.";
	public static final String INVALID_CONSTITUENCY_NUMBER = "Constituency number should be unique.";
	public static final String INVALID_DESCRIPTION = "Invalid description.";
	public static final String INVALID_URL = "Invalid URL. Please provide a valid URL.";
	public static final String INVALID_PARTYNAME = "Invalid party name. Please provide a proper name.";
	public static final String INVALID_OBJECT = "Invalid object.";
	public static final String INVALID_CANDIDATE_ID = "Invalid candidate ID.";
	public static final String INVALID_VOTES = "Invalid vote count. Please provide the correct number of votes.";
	public static final String INVALID_CONSTITUENCY_ID = "Invalid constituency ID.";
	public static final String INVALID_AFFIDAVIT_ID = "Invalid affidavit ID.";
	public static final String INVALID_ELECTION_ID = "Invalid election ID.";
	public static final String INVALID_ELECTIPTION_TYPE = "Invalid election type.";
	
}
