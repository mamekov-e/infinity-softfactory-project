package com.shop.onlineshop.repositories;

import com.shop.onlineshop.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory_CategoryId(long CategoryId);

    List<Product> findProductByProductNameStartingWith(String productName);



}
