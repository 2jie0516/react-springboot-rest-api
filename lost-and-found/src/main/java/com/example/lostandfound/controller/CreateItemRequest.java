package com.example.lostandfound.controller;

import com.example.lostandfound.model.Category;

public record CreateItemRequest(String itemName, Category category, String place, String description) {
}
