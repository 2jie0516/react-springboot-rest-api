package com.example.lostandfound.controller;

import com.example.lostandfound.service.FoundService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class ItemController {

    private final FoundService itemService;

    public ItemController(FoundService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public String productsPage(Model model) {
        var items = itemService.getAllItem();
        model.addAttribute("items", items);
        return "item-list";
    }

    @GetMapping("new-item")
    public String newItemPage() {
        return "new-item";
    }

    @PostMapping("/items")
    public String newProduct(CreateItemRequest createItemRequest) {
        itemService.createItem(
                createItemRequest.itemName(),
                createItemRequest.category(),
                createItemRequest.place(),
                createItemRequest.description());
        return "redirect:/products";
    }
}
