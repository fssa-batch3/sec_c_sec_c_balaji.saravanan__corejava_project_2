
package com.fssa.politifact.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fssa.politifact.dao.LeaderDao;
import com.fssa.politifact.model.Leaders;
import com.fssa.politifact.validator.LeaderValidateError;
import com.fssa.politifact.validator.LeaderValidateException;
import com.fssa.politifact.validator.LeaderValidator;

/**
 * @author BalajiSaravanan
 *
 */
public class LeaderService {

	public LeaderValidator leaderValidator;

	public LeaderDao leaderDao;
 
	public LeaderService(LeaderValidator leaderValidator, LeaderDao leader) {

		this.leaderValidator = leaderValidator;

		this.leaderDao = leader;

	}

	public boolean addLeader(Leaders leader) throws LeaderValidateException, SQLException {

		if (leader == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_LEADER_NULL);

		}

		if (this.leaderValidator.validate(leader)) {

			return this.leaderDao.addLeader(leader);

		} else {

			return false;

		}
	}

	public boolean upDateLeader(Leaders leader) throws LeaderValidateException, SQLException {

		if (leader == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_LEADER_NULL);

		}

		if (this.leaderValidator.validate(leader)) {

			return this.leaderDao.updateLeader(leader);

		} else {

			return false;

		}
		
	}

	public boolean deleteLeader(int leader) throws LeaderValidateException, SQLException {
		if (leader < 0) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_CANDIDATE_ID);

		}

		if (leader > 0) {

			return this.leaderDao.deleteLeader(leader);

		} else {
			return false;

		}
	}
	
	
	
	public ArrayList<Leaders> callAllLeader() throws LeaderValidateException, SQLException {
		
			return this.leaderDao.readLeader();
		
	}
	
//	public ArrayList<Leaders> readLeaderWithConstituency()throws LeaderValidateException, SQLException {
//		
//		return this.leaderDao.readLeaderWithConstituency();
//	}
}
