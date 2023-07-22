package com.example.lostandfound.service;

import com.example.lostandfound.model.Category;
import com.example.lostandfound.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FoundService {
    List<Item> getAllItem();
    Item createItem(String itemName,Category category,String place,String description);
    Item findById(int itemId);
}
