package it.objectmethod.ecommerce.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.spring.models.StatusObject;
import it.objectmethod.ecommerce.spring.services.StatusService;

@RestController

public class StatusController {

	@Autowired
	private StatusService statusService;

	@GetMapping("/status/get-one/{id}")
	public StatusObject getStatusById(@PathVariable Long id) {
		return statusService.getStatusById(id);
	}

}
