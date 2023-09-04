package com.fssa.politifact.validator;

public class LeaderValidateError {

	private LeaderValidateError() {

	}
	/**
	 * this is consent message so we store.
	 */
	/*
	 * Constants for representing various validation error messages.
	 */

	// Indicates that a Leader object cannot be null.
	public static final String INVALID_LEADER_NULL = "Leader object cannot be null.";

	// Indicates an invalid leader name. Check for length, pattern, and nullify.
	public static final String INVALID_NAME = "Invalid leader name. Check length, pattern, and nullity.";

	// Indicates that a Leader's position cannot be null.
	public static final String INVALID_POSITION = "Leader's position cannot be null.";

	// Indicates that a Party ID cannot be less than zero.
	public static final String INVALID_PARTY_ID = "Party ID cannot be less than zero.";

	// Indicates that a Leader's experience should be greater than -1.
	public static final String INVALID_EXPERIENCE = "Leader's experience should be greater than -1.";

	// Indicates that a Leader's occupation cannot be null and must only contain alphabet letters.
	public static final String INVALID_OCCUPATION = "Leader's occupation cannot be null and only accepts alphabet letters.";

	// Indicates that a Constituency name cannot be null and should have a length greater than 2.
	public static final String INVALID_CONSTITUENCY_NAME = "Constituency name cannot be null and length should be greater than 2. and give correct available constituency name";

	// Indicates that a Constituency number should be unique.
	public static final String INVALID_CONSTITUENCY_NUMBER = "Constituency number should be unique.";

	// Indicates an invalid description.
	public static final String INVALID_DESCRIPTION = "Invalid description.";

	// Indicates an invalid URL. A valid URL is expected.
	public static final String INVALID_URL = "Invalid URL. Please provide a valid URL.";

	// Indicates an invalid party name. A proper name is expected.
	public static final String INVALID_PARTYNAME = "Invalid party name. Please provide a proper name.";

	// Indicates an invalid object.
	public static final String INVALID_OBJECT = "Invalid object.";

	// Indicates an invalid candidate ID.
	public static final String INVALID_CANDIDATE_ID = "Invalid candidate ID.";

	// Indicates an invalid constituency ID.
	public static final String INVALID_CONSTITUENCY_ID = "Invalid constituency ID.";

	// Indicates an invalid affidavit ID.
	public static final String INVALID_AFFIDAVIT_ID = "Invalid affidavit ID.";

	// Indicates an invalid election ID.
	public static final String INVALID_ELECTION_ID = "Invalid election ID.";

	// Indicates an invalid election type.
	public static final String INVALID_ELECTION_TYPE = "Invalid election type.";

	// Indicates an invalid election year. The year should be greater than 1900.
	public static final String INVALID_ELECTION_YEAR = "Invalid election year. Please provide a year greater than 1900.";

}
