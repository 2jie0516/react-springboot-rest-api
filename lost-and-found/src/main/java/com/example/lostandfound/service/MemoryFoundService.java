package com.example.lostandfound.service;

import com.example.lostandfound.model.Category;
import com.example.lostandfound.model.Item;
import com.example.lostandfound.repository.ItemRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MemoryFoundService implements FoundService{
    private final ItemRepository itemRepository;

    public MemoryFoundService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    @Override
    public List<Item> getItemsByCategory(Category category) {
        return itemRepository.findByCategory(category);
    }

    @Override
    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    @Override
    public Item createItem(String itemName, Category category, String place, String description) {
        var product = new Item(itemName, category, place, description);
        return itemRepository.insert(product);
    }
}
