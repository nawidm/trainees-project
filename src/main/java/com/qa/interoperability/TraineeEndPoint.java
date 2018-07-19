package com.qa.interoperability;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import com.qa.business.service.TraineeService;

@Path("/trainee")
public class TraineeEndPoint {

	private static final Logger LOGGER = Logger.getLogger(TraineeEndPoint.class);
	
	@Inject
	private TraineeService service;

	@Path("/json")
	@GET
	@Produces({ "application/json" })
	public String getAllTrainees() {
		LOGGER.info("In TraineeEndPoint getAllTrainees");
		return service.getAllTrainees();
	}

	@Path("/json")
	@POST
	@Produces({ "application/json" })
	public String addTrainee(String trainee) {
		LOGGER.info("In TraineeEndPoint addTrainee");
		return service.addTrainee(trainee);
	}

	@Path("/json/{id}")
	@PUT
	@Produces({ "application/json" })
	public String updateTrainee(@PathParam("id") Long id, String trainee) {
		LOGGER.info("In TraineeEndPoint updateTrainee");
		return service.updateTrainee(id, trainee);
	}

	@Path("/json/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteTrainee(@PathParam("id") Long id) {
		LOGGER.info("In TraineeEndPoint deleteTrainee");
		return service.deleteTrainee(id);

	}

	public void setService(TraineeService service) {
		LOGGER.info("In TraineeEndPoint setService");
		this.service = service;
	}

}