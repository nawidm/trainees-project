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
		LOGGER.info("In TraineeDBRepo getAllTrainees");
		Query query = manager.createQuery("Select a FROM Trainee a");
		Collection<Trainee> trainee = (Collection<Trainee>) query.getResultList();
		return util.getJSONForObject(trainee);
	}

	@Transactional(REQUIRED)
	public String createTrainee(String trainee) {
		LOGGER.info("In TraineeDBRepo createTrainee");
		Trainee aTrainee = util.getObjectForJSON(trainee, Trainee.class);
		manager.persist(aTrainee);
		return "{\"message\": \"trainee has been sucessfully added\"}";
	}

	@Transactional(REQUIRED)
	public String updateTrainee(Long id, String traineeToUpdate) {
		LOGGER.info("In TraineeDBRepo updateTrainee");
		Trainee updatedTrainee = util.getObjectForJSON(traineeToUpdate, Trainee.class);
		Trainee traineeFromDB = findTrainee(id);
		if (traineeToUpdate != null) {
			LOGGER.info("Trainee passed is not null");
			traineeFromDB = updatedTrainee;
			traineeFromDB.setId(id);
			manager.merge(traineeFromDB);		
			return "{\"message\": \"trainee sucessfully updated\"}";
		} else {
			LOGGER.error("Trainee passed is null");
			return "{\"message\": \"trainee not updated\"}";
		}

	}

	@Transactional(REQUIRED)
	public String deleteTrainee(Long id) {
		LOGGER.info("In TraineeDBRepo deleteTrainee");
		Trainee traineeInDB = findTrainee(id);
		if (traineeInDB != null) {
			LOGGER.info("Trainee with given id exists in DB");
			manager.remove(traineeInDB);
		}
		return "{\"message\": \"Trainee sucessfully deleted\"}";
	}

	private Trainee findTrainee(Long id) {
		LOGGER.info("In TraineeDBRepo findTrainee");
		return manager.find(Trainee.class, id);
	}

	public void setManager(EntityManager manager) {
		LOGGER.info("In TraineeDBRepo setManager");
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}