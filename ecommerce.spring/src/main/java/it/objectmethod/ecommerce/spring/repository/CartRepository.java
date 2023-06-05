package it.objectmethod.ecommerce.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.ecommerce.spring.models.CartObject;

@Repository
public interface CartRepository extends JpaRepository<CartObject, Long> {

//	@Query(value = "SELECT * FROM cart WHERE user_id=:userId", nativeQuery = true)
	CartObject findByUserIdId(Long userId);
}
