package com.example.lostandfound.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Item {
    private final UUID itemId;
    private String itemName;
    private Category category;
    private final String foundPlace;
    private final String description;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Item(UUID itemId, String itemName, Category category, String foundPlace, String description) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.foundPlace = foundPlace;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public UUID getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public Category getCategory() {
        return category;
    }

    public String getFoundPlace() {
        return foundPlace;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
        updatedAt = LocalDateTime.now();
    }

    public void setCategory(Category category) {
        this.category = category;
        updatedAt = LocalDateTime.now();
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        updatedAt = LocalDateTime.now();
    }
}
