package com.example.lostandfound.repository;

import com.example.lostandfound.model.Category;
import com.example.lostandfound.model.Item;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository {
    List<Item> findAll();

    Item insert(Item item);

    Item update(Item item);
}
