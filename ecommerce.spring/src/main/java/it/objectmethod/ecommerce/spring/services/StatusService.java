package it.objectmethod.ecommerce.spring.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.ecommerce.spring.models.StatusObject;
import it.objectmethod.ecommerce.spring.repository.StatusRepository;

@Service
public class StatusService {

	@Autowired
	private StatusRepository statusRepository;

	public StatusObject getStatusById(Long id) {
		return statusRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Status not found"));
	}

}
