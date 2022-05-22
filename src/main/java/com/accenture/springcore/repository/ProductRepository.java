package com.accenture.springcore.repository;

import com.accenture.springcore.model.Product;
import com.accenture.springcore.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product, Integer> {

}
