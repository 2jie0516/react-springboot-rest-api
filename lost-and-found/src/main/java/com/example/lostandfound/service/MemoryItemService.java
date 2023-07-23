package com.example.lostandfound.service;

import com.example.lostandfound.model.Category;
import com.example.lostandfound.model.Item;
import com.example.lostandfound.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoryItemService implements ItemService {
    private final ItemRepository itemRepository;

    public MemoryItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    @Override
    public Item createItem(String itemName, Category category, String place, String description) {
        var item = new Item(itemName, category, place, description);
        return itemRepository.insert(item);
    }

    @Override
    public Item findById(int itemId) {
        return itemRepository.findById(itemId);
    }
}
