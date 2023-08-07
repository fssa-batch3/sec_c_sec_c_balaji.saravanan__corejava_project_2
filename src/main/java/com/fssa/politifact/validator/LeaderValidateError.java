package com.fssa.politifact.validator;

public interface LeaderValidateError {

	public static final String INVALID_LEADER_NULL = "Leader object can't be null";
	public static final String INVALID_NAME = "Leader name can't be null ";
	public static final String INVALID_POSITION = "Leader's position can't be null";
	public static final String INVALID_PARTY_ID = "Party id can't be less than zero";
	public static final String INVALID_EXPERIENCE = "Leader experience can't be null ";
	public static final String INVALID_OCCUPATION = "Leader occupation can't be null";
	public static final String INVALID_STARTING_DATE = "Invalid date";
	public static final String INVALID_ENDING_DATE = "Invalid date";
	public static final String INVALID_CONSTITUENCY_NAME = "counstuency name can't be null";
	public static final String INVALID_CONSTITUENCY_NUMBER = "constuency name should be unique";
	public static final String INVALID_DESCRIPTION = "Invalid description";
	public static final String INVALID_URL = "Invalid url, Give proper url";
	public static final String INVALID_PARTYNAME= "Invalid party name give proper name";
	public static final String INVALID_OBJECT ="invalid object ";
    public static final String INVALID_CANDIDATE_ID="Invalid candidate Id";
    public static final String INVALID_VOTES="invalid votes give correct nomer of votes";
    public static final String INVALID_CONSTITUENCY_ID="invalid constituency id";
    public static final String INVALID_AFFIDAVIT_ID="Invalidate affidavit Id";
    public static final String INVALID_ELECTION_ID="Invalid election id";
    public static final String INVALID_ELECTIPTION_TYPE="Invalidate election type";
}
