package com.challenge.ivms.service;


import java.util.List;
import com.challenge.ivms.model.Inventory;
import com.challenge.ivms.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public void deleteInventoryById(Long id) {
        inventoryRepository.deleteById(id);
    }

    public void validateInventory(Long itemId, int quantity) {
        int availableInventory = getAvailableInventory(itemId);
        if (quantity > availableInventory) {
            throw new IllegalArgumentException("Insufficient inventory for item " + itemId);
        }
    }

    public int getAvailableInventory(Long itemId) {
        Inventory inventory = inventoryRepository.findByItemId(itemId).orElse(null);
        if (inventory == null) {
            throw new IllegalArgumentException("Invalid item id: " + itemId);
        }
        return inventory.getQuantity();
    }


    public void updateInventory(Long itemId, int quantity) {
        Inventory inventory = inventoryRepository.findByItemId(itemId).orElseThrow(() ->
                new IllegalArgumentException("Invalid item id: " + itemId));

        int updatedQuantity = inventory.getQuantity() - quantity;
        if (updatedQuantity < 0) {
            throw new IllegalArgumentException("Insufficient inventory for item " + itemId);
        }

        inventory.setQuantity(updatedQuantity);
        inventoryRepository.save(inventory);
    }
}

