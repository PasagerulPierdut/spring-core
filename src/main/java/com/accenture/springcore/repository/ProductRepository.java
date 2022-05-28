package com.accenture.springcore.repository;

import com.accenture.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product, Integer> {

}
