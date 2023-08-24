
package com.fssa.politifact.service;

import java.sql.SQLException;
import java.util.List;

import com.fssa.politifact.dao.LeaderDao;
import com.fssa.politifact.exceptions.DaoException;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Constituency;
import com.fssa.politifact.model.Leader;
import com.fssa.politifact.validator.LeaderValidateError;
import com.fssa.politifact.validator.LeaderValidator;

/**
 * 
 * @author BalajiSaravanan
 *
 */
public class LeaderService {

	public final LeaderValidator leaderValidator;

	public final LeaderDao leaderDao;

	public LeaderService(LeaderValidator leaderValidator, LeaderDao leader) {

		this.leaderValidator = leaderValidator;

		this.leaderDao = leader;

	}  
	/**
	 * add leader doing check all object atrributes and then send value in dao.
	 * @param leader
	 * @return
	 * @throws LeaderValidateException
	 * @throws DaoException 
	 */

	public boolean addLeader(Leader leader) throws LeaderValidateException, DaoException {

		if (leader == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_LEADER_NULL);

		}

		if (this.leaderValidator.validate(leader)) {

			return this.leaderDao.addLeader(leader);

		} else {

			return false;

		}
	}
	
	/**
	 * update leader receive two parameter one is object , and another one is update name
	 * @param leader
	 * @param name
	 * @return
	 * @throws LeaderValidateException
	 * @throws DaoException
	 * @throws SQLException
	 */

	public boolean upDateLeader(Leader leader, String name) throws LeaderValidateException, DaoException, SQLException {

		if (leader == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_LEADER_NULL);

		}

		if (this.leaderValidator.validate(leader)) {

			return this.leaderDao.updateLeader(leader, name);

		} else {

			return false;

		}

	}
	
	/**
	 * delete leader delete the row of leader in database 
	 * @param leader
	 * @return
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException
	 */

	public boolean deleteLeader(String leader) throws LeaderValidateException, SQLException, DaoException {
		if (leader == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_NAME);
		}

		if (leaderValidator.validateName(leader)) {

			return this.leaderDao.deleteLeader(leader);

		} else {
			return false;

		}
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws DaoException
	 * @throws LeaderValidateException 
	 */

	public List<Leader> callAllLeader() throws SQLException, DaoException, LeaderValidateException {

		return this.leaderDao.readLeader();

	}
	
	/**
	 * 
	 * @return
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException
	 */

	public List<String> readAllLeader() throws LeaderValidateException, SQLException, DaoException {

		return this.leaderDao.readAllJoin();
	}
}
