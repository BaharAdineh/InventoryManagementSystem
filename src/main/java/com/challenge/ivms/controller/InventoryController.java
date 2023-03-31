package com.challenge.ivms.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.challenge.ivms.model.Inventory;
import com.challenge.ivms.service.InventoryService;

@Controller
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/")
    public String home(Model model) {
        final List<Inventory> inventories = inventoryService.getAllInventories();
        model.addAttribute("inventories", inventories);
        return "home";
    }

    @GetMapping("/inventory/{id}")
    public String viewInventory(@PathVariable Long id, Model model) {
        final Inventory inventory = inventoryService.getInventoryById(id);
        model.addAttribute("inventory", inventory);
        return "view_inventory";
    }

    @GetMapping("/inventory/new")
    public String showAddInventoryForm() {
        return "add_inventory";
    }

    @PostMapping("/inventory/new")
    public String addInventory(@ModelAttribute("inventory") Inventory inventory, BindingResult result) {
        if (result.hasErrors()) {
            return "inventory-form";
        }
        inventoryService.addInventory(inventory);
        return "redirect:/";
    }


    @GetMapping("/inventory/{id}/edit")
    public String showEditInventoryForm(@PathVariable Long id, Model model) {
        final Inventory inventory = inventoryService.getInventoryById(id);
        model.addAttribute("inventory", inventory);
        return "edit_inventory";
    }

    @PostMapping("/inventory/{id}/edit")
    public String editInventory(@PathVariable Long id,
                                @RequestParam("name") String name,
                                @RequestParam("description") String description,
                                @RequestParam("quantity") int quantity) {
        final Inventory inventory = inventoryService.getInventoryById(id);
        inventory.setName(name);
        inventory.setDescription(description);
        inventory.setQuantity(quantity);
        inventoryService.updateInventory(inventory);
        return "redirect:/inventory/" + id;
    }

    @PostMapping("/inventory/{id}/delete")
    public String deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return "redirect:/";
    }
}
