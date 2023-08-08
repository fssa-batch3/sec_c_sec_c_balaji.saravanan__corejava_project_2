package com.fssa.politifact.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fssa.politifact.dao.ElectionDao;
import com.fssa.politifact.model.Election;
import com.fssa.politifact.validator.ElectionValidator;
import com.fssa.politifact.validator.LeaderValidateError;
import com.fssa.politifact.validator.LeaderValidateException;

public class ElectionService {

	private ElectionValidator electionValidator;

	private ElectionDao electionDao;

	public ElectionService(ElectionValidator electionValidator, ElectionDao electionDao) {
		
		this.electionValidator = electionValidator;
		this.electionDao = electionDao;
	}

	public boolean addElection(Election election) throws LeaderValidateException, SQLException {

		if (election == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);

		}

		if (this.electionValidator.validate(election)) {

			return this.electionDao.addElection(election);
		} else {

			return false;

		}

	}

	public boolean upDateElection(Election election) throws LeaderValidateException, SQLException {

		if (election == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);

		} 

		if (this.electionValidator.validate(election)) {

			return this.electionDao.updateElection(election);
		} else {

			return false;

		}

	}

	public boolean deleteElection(int electionId) throws LeaderValidateException, SQLException {
		if (electionId < 0) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_CANDIDATE_ID);

		}

		if (electionId > 0) {

			return this.electionDao.deleteElection(electionId);

		} else {
			return false;

		}
	}

	public ArrayList<Election> electionList() throws LeaderValidateException, SQLException {

		return this.electionDao.readAllElection();

	}

}
