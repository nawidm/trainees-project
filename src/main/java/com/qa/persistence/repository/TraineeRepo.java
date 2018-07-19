package com.qa.persistence.repository;

public interface TraineeRepo {

	String getAllTrainees();

	String createTrainee(String accout);

	String updateTrainee(Long id, String accountToUpdate);

	String deleteTrainee(Long id);

}
