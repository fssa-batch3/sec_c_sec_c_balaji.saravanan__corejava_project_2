package com.fssa.politifact.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.model.Election;
import com.fssa.politifact.model.ElectionTypes;

public class ElectionValidatorTest {

    private ElectionValidator electionValidator;

    @BeforeEach
    void setUp() {
        electionValidator = new ElectionValidator();
    }

    @Test
    void testValidElection() throws LeaderValidateException {
        Election election = new Election(1, 2023, ElectionTypes.ASSEMBLY_ELECTION, 1001);
        boolean result = electionValidator.validate(election);
        assertEquals(true, result);
    }

    @Test
    void testNullElection() {
        assertThrows(LeaderValidateException.class, () -> electionValidator.validate(null));
    }

    @Test
    void testInvalidElectionYear() {
    	
        Election election = new Election(1, 1800, ElectionTypes.LOCALA_ELECTION, 1002);
        
        assertThrows(LeaderValidateException.class, () -> electionValidator.validate(election));
    }

    @Test
    void testNullElectionType() {
    	
        Election election = new Election(1, 2023, null, 1003);
        
        assertThrows(LeaderValidateException.class, () -> electionValidator.validate(election));
    }

    @Test
    void testInvalidLeaderId() {
    	
        Election election = new Election(1, 2023, ElectionTypes.ASSEMBLY_ELECTION, -1);
        
        assertThrows(LeaderValidateException.class, () -> electionValidator.validate(election));
    }
}