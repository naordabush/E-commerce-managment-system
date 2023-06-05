package it.objectmethod.ecommerce.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.ecommerce.spring.models.StatusObject;

@Repository
public interface StatusRepository extends JpaRepository<StatusObject, Long> {

}
