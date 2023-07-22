package com.example.lostandfound.repository;

import com.example.lostandfound.model.Category;
import com.example.lostandfound.model.Item;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepository {
    List<Item> findAll();

    Item insert(Item item);

    Item update(Item item);

    Optional<Item> findById(UUID itemId);

    Optional<Item> findByName(String itemName);

    List<Item> findByCategory(Category category);

    void deleteAll();
}