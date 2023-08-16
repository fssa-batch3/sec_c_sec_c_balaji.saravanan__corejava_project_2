package com.fssa.politifact.service;

import java.sql.SQLException;
import java.util.List;

import com.fssa.politifact.dao.ConstituencyDao;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Constituency;
import com.fssa.politifact.validator.ConstituencyValidator;
import com.fssa.politifact.validator.LeaderValidateError;

/*
 * this is service layer
 * this connect to the dao layer and validator 
 * given all value check the validator and then send the dao
 * this purpuse help to this layer.
 */

public class ConstituencyService {

	public final ConstituencyDao constituencyDao;

	public final ConstituencyValidator constituencyValidator;
	
	/*
	 * constiteuncy have a one constuctor
	 */
	 

	public ConstituencyService(ConstituencyValidator constituencyValidator, ConstituencyDao constituencyDao) {

		this.constituencyDao = constituencyDao; 

		this.constituencyValidator = constituencyValidator;
	} 

	/*
	 * this add constituency check the object help to the validator.
	 * and then send the object the dao layer.
	 */
	
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
	
	/*
	 * this update constituency check the object help to the validator.
	 * and then send the object the dao layer.
	 */

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
	
	/**
	 * @param constituency
	 * @return true or false
	 * @throws LeaderValidateException
	 * @throws SQLException
	 */

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
	
	/**
	 *  this constuency list return the arryList
	 * @return true or false
	 * @throws LeaderValidateException
	 * @throws SQLException
	 */

	public List<Constituency> constuencyList() throws LeaderValidateException, SQLException {

		return this.constituencyDao.readAllConstituencies();

	}

}  
