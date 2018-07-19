package com.qa.business.service;

public interface TraineeService {

	String getAllTrainees();

	String addTrainee(String account);

	String updateTrainee(Long id, String account);

	String deleteTrainee(Long id);

}
