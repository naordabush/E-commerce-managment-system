package it.objectmethod.ecommerce.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.spring.models.ItemObject;
import it.objectmethod.ecommerce.spring.services.ItemService;

@RestController

public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping("/items/get-all")
	public List<ItemObject> getAllItems() {
		return itemService.getAllItems();
	}

	@GetMapping("/items/get-one/{id}")
	public ItemObject getItemById(@PathVariable Long id) {
		return itemService.getItemById(id);
	}

	@PostMapping("/items/create")
	public ItemObject createItem(@RequestBody ItemObject item) {
		return itemService.createItem(item);
	}

	@PutMapping("/items/update/{id}")
	public ItemObject updateItem(@PathVariable Long id, @RequestBody ItemObject item) {
		return itemService.updateItem(id, item);
	}

	@DeleteMapping("/items/delete/{id}")
	public void deleteItem(@PathVariable Long id) {
		itemService.deleteItem(id);
	}

}
