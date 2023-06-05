package it.objectmethod.ecommerce.spring.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.ecommerce.spring.models.ItemObject;
import it.objectmethod.ecommerce.spring.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public List<ItemObject> getAllItems() {
		return itemRepository.findAll();
	}

	public ItemObject getItemById(Long id) {
		return itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item not found"));
	}

	public ItemObject createItem(ItemObject item) {
		return itemRepository.save(item);
	}

	public ItemObject updateItem(Long id, ItemObject item) {
		item.setId(id);
		return itemRepository.save(item);
	}

	public void deleteItem(Long id) {
		itemRepository.deleteById(id);
	}

}
