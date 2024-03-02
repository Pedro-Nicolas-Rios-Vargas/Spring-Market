package com.ordep.market.domain.service;

import com.ordep.market.domain.Product;
import com.ordep.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public boolean delete(int productId) {
        /*
         * Esta es una forma de manejar el delete
        if (getProduct(productId).isPresent()) {
            productRepository.delete(productId);
            return true;
        }
        return false;
         */
        /*
         Pero también hay que tener en consideración la magia
         de las Object Functions en java
         */

        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);
    }
}
