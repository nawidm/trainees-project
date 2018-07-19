package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Trainee;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class TraineeDBRepo implements TraineeRepo {

	private static final Logger LOGGER = Logger.getLogger(TraineeDBRepo.class);
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	public String getAllTrainees() {
		LOGGER.info("In AccountDBRepository getAllAccounts");
		Query query = manager.createQuery("Select a FROM Account a");
		Collection<Trainee> accounts = (Collection<Trainee>) query.getResultList();
		return util.getJSONForObject(accounts);
	}

	@Transactional(REQUIRED)
	public String createTrainee(String trainee) {
		LOGGER.info("In AccountDBRepository createAccount");
		Trainee aTrainee = util.getObjectForJSON(trainee, Trainee.class);
		manager.persist(aTrainee);
		return "{\"message\": \"account has been sucessfully added\"}";
	}

	@Transactional(REQUIRED)
	public String updateTrainee(Long id, String traineeToUpdate) {
		LOGGER.info("In AccountDBRepository updateAccount");
		Trainee updatedTrainee = util.getObjectForJSON(traineeToUpdate, Trainee.class);
		Trainee traineeFromDB = findTrainee(id);
		if (traineeToUpdate != null) {
			LOGGER.info("Account passed is not null");
			traineeFromDB = updatedTrainee;
			traineeFromDB.setId(id);
			manager.merge(traineeFromDB);		
			return "{\"message\": \"account sucessfully updated\"}";
		} else {
			LOGGER.error("Account passed is null");
			return "{\"message\": \"account not updated\"}";
		}

	}

	@Transactional(REQUIRED)
	public String deleteTrainee(Long id) {
		LOGGER.info("In AccountDBRepository deleteAccount");
		Trainee traineeInDB = findTrainee(id);
		if (traineeInDB != null) {
			LOGGER.info("Account with given id exists in DB");
			manager.remove(traineeInDB);
		}
		return "{\"message\": \"account sucessfully deleted\"}";
	}

	private Trainee findTrainee(Long id) {
		LOGGER.info("In AccountDBRepository findAccount");
		return manager.find(Trainee.class, id);
	}

	public void setManager(EntityManager manager) {
		LOGGER.info("In AccountDBRepository setManager");
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}