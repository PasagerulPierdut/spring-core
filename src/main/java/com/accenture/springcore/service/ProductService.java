package com.accenture.springcore.service;

import com.accenture.springcore.exception.customExceptions.EntityNotFoundException;
import com.accenture.springcore.model.Product;
import com.accenture.springcore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService extends BaseService<Product, Integer> {

    private final String NOT_PRESENT = "The product with the specified ID does not exist in database.";

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findOneById(Integer id) {
        return productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(NOT_PRESENT));
    }

    public Product updateProduct(Integer id, Product product) {
        Optional<Product> opt = productRepository.findById(id);
        if (opt.isPresent()) {
            Product repoProduct = opt.get();
            repoProduct.setName(product.getName());
            repoProduct.setDescription(product.getDescription());
            repoProduct.setModifiedAt(LocalDateTime.now());
            return productRepository.save(repoProduct);
        } else {
            throw new EntityNotFoundException(NOT_PRESENT);
        }
    }

    public void deleteProduct(Integer id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(NOT_PRESENT);
        }
    }
}

