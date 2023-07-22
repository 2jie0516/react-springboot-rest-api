package com.example.lostandfound.controller;

import com.example.lostandfound.model.Category;
import com.example.lostandfound.model.Status;

public record CreateItemRequest(String itemName, Category category, String place, String description) {
}
