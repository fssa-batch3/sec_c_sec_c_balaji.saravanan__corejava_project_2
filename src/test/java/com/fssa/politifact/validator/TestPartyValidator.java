package com.fssa.politifact.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Party;

 class TestPartyValidator {

	private PartyValidator partyValidator;

	@BeforeEach
	public void setUp() {
		partyValidator = new PartyValidator();
	} 

	@Test
	 void testValidParty() throws LeaderValidateException {
		
		Party party = new Party( "PartyName", "https://www.example.com/party.jpg");
		Assertions.assertDoesNotThrow(() -> partyValidator.validate(party));
		
	}
	
	@Test
	 void testValidPartyNull() throws LeaderValidateException {
		LeaderValidateException exception = Assertions.assertThrows(LeaderValidateException.class,
				() -> partyValidator.validate(null));
		Assertions.assertEquals(LeaderValidateError.INVALID_OBJECT, exception.getMessage());
		
		  
	}

	@Test
	 void testInvalidPartyName() {
		Party party = new Party( null, "https://www.example.com/party.jpg");
		LeaderValidateException exception = Assertions.assertThrows(LeaderValidateException.class,
				() -> partyValidator.validate(party));
		Assertions.assertEquals(LeaderValidateError.INVALID_PARTYNAME, exception.getMessage());
	}
	@Test
	 void testInvalidPartyNameEmpty() {
		Party party = new Party( "", "https://www.example.com/party.jpg");
		LeaderValidateException exception = Assertions.assertThrows(LeaderValidateException.class,
				() -> partyValidator.validate(party));
		Assertions.assertEquals(LeaderValidateError.INVALID_PARTYNAME, exception.getMessage());
	}
	@Test
	public void testInvalidPartyNamePattern() {
		Party party = new Party( "2345678", "https://www.example.com/party.jpg");
		LeaderValidateException exception = Assertions.assertThrows(LeaderValidateException.class,
				() -> partyValidator.validate(party));
		Assertions.assertEquals(LeaderValidateError.INVALID_PARTYNAME, exception.getMessage());
	}

	@Test
	 void testInvalidPartyImageUrl() { 
		Party party = new Party("PartyName", null);
		LeaderValidateException exception = Assertions.assertThrows(LeaderValidateException.class,
				() -> partyValidator.validate(party));
		Assertions.assertEquals(LeaderValidateError.INVALID_URL, exception.getMessage());
	}
	@Test
	 void testInvalidPartyImageUrlEmpty() {
		Party party = new Party( "PartyName", "");
		LeaderValidateException exception = Assertions.assertThrows(LeaderValidateException.class,
				() -> partyValidator.validate(party));
		Assertions.assertEquals(LeaderValidateError.INVALID_URL, exception.getMessage());
	}
	@Test
	 void testInvalidPartyImageUrlPattern() {
		Party party = new Party("PartyName", "234567890");
		LeaderValidateException exception = Assertions.assertThrows(LeaderValidateException.class,
				() -> partyValidator.validate(party));
		Assertions.assertEquals(LeaderValidateError.INVALID_URL, exception.getMessage());
	}
}