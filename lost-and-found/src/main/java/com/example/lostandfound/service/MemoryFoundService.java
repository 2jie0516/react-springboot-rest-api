package com.example.lostandfound.service;

import com.example.lostandfound.model.Category;
import com.example.lostandfound.model.Item;

import java.util.List;

public class MemoryFoundService implements FoundService{
    private final ProductRepository productRepository;
    @Override
    public List<Item> getItemsByCategory(Category category) {
        return null;
    }

    @Override
    public List<Item> getAllItem() {
        return null;
    }

    @Override
    public Item createItem(String itemName, Category category, String place, String description) {
        return null;
    }
}
