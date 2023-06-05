package it.objectmethod.ecommerce.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.objectmethod.ecommerce.spring.models.CartItemObject;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemObject, Long> {

	@Query(value = "SELECT * FROM cart_item WHERE cart_id=:cartId AND item_id=:itemId", nativeQuery = true)
	CartItemObject findByCartIdAndItemId(@Param("cartId") Long cartId, @Param("itemId") Long itemId);

	List<CartItemObject> findAllByCartId(Long userId);

	@Query("SELECT c FROM CartItemObject c WHERE c.orderTime BETWEEN :startDate AND :endDate")
	List<CartItemObject> findByOrderTimeBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
