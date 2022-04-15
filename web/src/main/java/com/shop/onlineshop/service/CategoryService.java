package com.shop.onlineshop.service;

import com.shop.onlineshop.model.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void addCategory(Category category);

    List<Category> listCategory();

    void deleteCategory(long categoryId);

    void updateCategory(Category category);

    Optional<Category> getCategory(long categoryId);
}
