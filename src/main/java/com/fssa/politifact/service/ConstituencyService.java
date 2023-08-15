package com.fssa.politifact.service;

import java.sql.SQLException;
import java.util.List;

import com.fssa.politifact.dao.ConstituencyDao;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Constituency;
import com.fssa.politifact.validator.ConstituencyValidator;
import com.fssa.politifact.validator.LeaderValidateError;

public class ConstituencyService {

	public final ConstituencyDao constituencyDao;

	public final ConstituencyValidator constituencyValidator;

	public ConstituencyService(ConstituencyValidator constituencyValidator, ConstituencyDao constituencyDao) {

		this.constituencyDao = constituencyDao; 

		this.constituencyValidator = constituencyValidator;
	}

	public boolean addConstituency(Constituency constituency) throws LeaderValidateException, SQLException {

		if (constituency == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);

		}

		if (this.constituencyValidator.validate(constituency)) {

			return this.constituencyDao.addConstituency(constituency);
		} else {

			return false;

		}

	} 

	public boolean upDateConstituency(Constituency constituency ,String constituencyName) throws LeaderValidateException, SQLException {

		if (constituency == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);

		}

		if (this.constituencyValidator.validate(constituency)) {

			return this.constituencyDao.updateConstituency(constituency, constituencyName);
			
		} else {

			return false;
 
		}

	}

	public boolean deleteConstituency(int constituency) throws LeaderValidateException, SQLException {
		if (constituency < 0) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_CANDIDATE_ID);

		}

		if (constituency > 0) {

			return this.constituencyDao.deleteConstituency(constituency);

		} else {
			return false;

		}
	} 

	public List<Constituency> constuencyList() throws LeaderValidateException, SQLException {

		return this.constituencyDao.readAllConstituencies();

	}

}  
