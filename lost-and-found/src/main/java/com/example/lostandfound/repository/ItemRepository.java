package com.example.lostandfound.repository;

import com.example.lostandfound.model.Category;
import com.example.lostandfound.model.Item;
import com.example.lostandfound.model.Status;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository {
    List<Item> findAll();

    Item insert(Item item);

    void update(int itemId, Status status);
    Item findById(int itemId);
}
