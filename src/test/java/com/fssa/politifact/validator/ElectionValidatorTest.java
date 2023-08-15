package com.fssa.politifact.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.enums.ElectionTypes;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Election;

 class ElectionValidatorTest {

    private ElectionValidator electionValidator;

    @BeforeEach
    void setUp() { 
        electionValidator = new ElectionValidator();
    }

    @Test
    void testValidElection() throws LeaderValidateException {
        Election election = new Election(1,2023, ElectionTypes.ASSEMBLY_ELECTION);
        boolean result = electionValidator.validate(election);
        assertEquals(true, result);
    }

    @Test
    void testNullElection() {
        assertThrows(LeaderValidateException.class, () -> electionValidator.validate(null));
    }

    @Test
    void testInvalidElectionYear() {
    	
        Election election = new Election(1, 1800, ElectionTypes.LOCAL_ELECTION);
        
        assertThrows(LeaderValidateException.class, () -> electionValidator.validate(election));
    }

    @Test
    void testNullElectionType() {
    	
        Election election = new Election(1,  2023, null);
        
        assertThrows(LeaderValidateException.class, () -> electionValidator.validate(election));
    }

   
}