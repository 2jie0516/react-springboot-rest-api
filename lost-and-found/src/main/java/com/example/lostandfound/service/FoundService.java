package com.example.lostandfound.service;

import com.example.lostandfound.model.Category;
import com.example.lostandfound.model.Item;

import java.util.List;

public interface FoundService {
    List<Item> getItemsByCategory(Category category);
    List<Item> getAllItem();
    Item createItem(String itemName,Category category,String place,String description);
}
