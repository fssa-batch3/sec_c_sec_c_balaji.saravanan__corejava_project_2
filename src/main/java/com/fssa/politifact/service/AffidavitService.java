package com.fssa.politifact.service;

import java.sql.SQLException;
import java.util.List;

import com.fssa.politifact.dao.AffidavitDao;
import com.fssa.politifact.dao.LeaderDao;
import com.fssa.politifact.exceptions.DaoException;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Affidavit;
import com.fssa.politifact.model.Leader;
import com.fssa.politifact.validator.AffidavitValidator;
import com.fssa.politifact.validator.LeaderValidateError;

/**
 * 
 * @author BalajiSaravanan
 *
 *         affidavit service connect to validator and dao layer
 */

public class AffidavitService {

	private AffidavitDao affidavitDao;
	private AffidavitValidator affidavitValidator;

	public AffidavitService(AffidavitDao affidavitDao, AffidavitValidator affidavitValidator) {

		this.affidavitDao = affidavitDao;
		this.affidavitValidator = affidavitValidator;

	}

	/**
	 * add affidavit
	 * 
	 * @param affidavit
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 * @throws LeaderValidateException
	 */

	public boolean addAffidavit(Affidavit affidavit) throws DaoException, SQLException, LeaderValidateException {

		if (affidavit == null) {

			throw new DaoException(LeaderValidateError.INVALID_OBJECT);

		}

		if (this.affidavitValidator.validate(affidavit)) {

			return this.affidavitDao.addAffidavit(affidavit);
		} else {

			return false;

		}

	}

	/**
	 * update affidavit change the any thig give id this is update the table.
	 * 
	 * @param affidavit
	 * @param id
	 * @return
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException
	 */

	public boolean upDateAffidavit(Affidavit affidavit, int id)
			throws LeaderValidateException, SQLException, DaoException {

		if (affidavit == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);

		}

		if (this.affidavitValidator.validate(affidavit)) {

			return this.affidavitDao.updateAffidavit(affidavit, id);
		} else {

			return false;

		}

	}

	/**
	 * delete affidavit
	 * 
	 * @param id
	 * @return
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException
	 */

	public boolean deleteAffidavit(int id) throws LeaderValidateException, SQLException, DaoException {
		if (id < 0) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_CANDIDATE_ID);

		}

		return this.affidavitDao.deleteAffidavit(id);

	}
	
	/**
	 * this is read all affidavit
	 * @return
	 * @throws SQLException
	 * @throws LeaderValidateException
	 * @throws DaoException
	 */
	

	public List<Affidavit> readAll() throws SQLException, LeaderValidateException, DaoException {

		return affidavitDao.readAllAffidavit();

	}
	
	/**
	 *this is read leader with particular Id
	 * @param id
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	

	public List<Affidavit> readSpecificLeader(int id) throws DaoException, SQLException {

		return affidavitDao.readAllLeaderWithAffidavit(id);
	}
	
	/**
	 * read specfic leader id with election
	 * @param id
	 * @param electionId
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	

	public List<Leader> readSpecificLeaderPartyId(int id ,int electionId ) throws DaoException, SQLException {

		return affidavitDao.readAllLeaderPartyId(id, electionId);   
	}
	
	
	/**
	 * read leader election only
	 * @param electionId
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	
	public List<Leader> readLeaderwithElection(int electionId) throws DaoException, SQLException {

		return affidavitDao.readAllLeaderInelection(electionId);   
	}
	
	/**
	 * read leader by election and constituency and party and election
	 * 
	 * @param id
	 * @param electionId
	 * @param constituencyId
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	
	
	public List<Leader> readSpecificLeaderPartyId(int id ,int electionId, int constituencyId) throws DaoException, SQLException {

		return affidavitDao.readAllLeaderPartyId(id, electionId,constituencyId);   
	}
	
	/**
	 * read leader for search purpose
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	
	
	public List<Leader> readAllLeaderSearch() throws DaoException, SQLException {

		return affidavitDao.readAllLeaderSearch();   
	}
	
	/**
	 * give constituency name get id
	 * @param constituecy
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	
	public int readConstituencyId(String constituecy) throws DaoException, SQLException {

		return LeaderDao.findConstituencyId(constituecy);   
	}
	
	
	public List<Leader> readeLeaderByconstituencyId(int constituencyId ,int electionId ) throws DaoException, SQLException {

		return affidavitDao.readAllLeaderbyconstituency(constituencyId, electionId);   
	}

	

}
