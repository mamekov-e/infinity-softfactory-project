package com.shop.onlineshop.service;

import com.shop.onlineshop.model.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public void addProduct(Product product);

    public List<Product> listProduct();

    public Optional<Product> getProductById(long productId);

    public List<Product> findByCategory(long categoryId);

    void deleteProduct(Long productId);

}
