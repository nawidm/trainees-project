package com.qa.business.service;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.persistence.repository.TraineeRepo;

public class TraineeServiceImpl implements TraineeService {

	private static final Logger LOGGER = Logger.getLogger(TraineeService.class);

	@Inject
	private TraineeRepo repo;

	public String getAllTrainees() {
		LOGGER.info("In TraineeServiceImpl getAllAccounts ");
		return repo.getAllTrainees();
	}

	public String addTrainee(String trainee) {
		LOGGER.info("In TraineeServiceImpl addAccount");
		return repo.createTrainee(trainee);
	}

	public String updateTrainee(Long id, String trainee) {
		LOGGER.info("In TraineeServiceImpl updateAccount");
		return repo.updateTrainee(id, trainee);
	}

	public String deleteTrainee(Long id) {
		LOGGER.info("In TraineeServiceImpl deleteAccount");
		return repo.deleteTrainee(id);

	}
	

	public void setRepo(TraineeRepo repo) {
		LOGGER.info("In TraineeServiceImpl setRepo");
		this.repo = repo;
	}

}
