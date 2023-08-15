
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

	public boolean addLeader(Leader leader) throws LeaderValidateException {

		if (leader == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_LEADER_NULL);

		}

		if (this.leaderValidator.validate(leader)) {

			return this.leaderDao.addLeader(leader);

		} else {

			return false;

		}
	}

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

	public List<String> callAllLeader() throws SQLException, DaoException {

		return this.leaderDao.readLeader();

	}

//	public ArrayList<Leaders> readLeaderWithConstituency()throws LeaderValidateException, SQLException {
//		
//		return this.leaderDao.readLeaderWithConstituency();
//	}
}
