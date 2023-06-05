package it.objectmethod.ecommerce.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.ecommerce.spring.models.ItemObject;

@Repository
public interface ItemRepository extends JpaRepository<ItemObject, Long> {

}
