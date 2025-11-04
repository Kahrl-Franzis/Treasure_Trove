package com.asis.service; // FIX 1: Corrected package name

import com.asis.entity.Product; // Import the Product entity
import com.asis.model.ProductCategory; // Assuming this model exists
import java.util.*;

public interface ProductService {

    // FIX 3: Changed ID type from Integer to Long (for consistency with entity)
    List<Product> getAllProducts();
    Product[] getAll();
    Product get(Long id); // FIX 3: Changed to Long
    Product create(Product product);
    Product update(Product product);
    void delete(Long id); // FIX 3: Changed to Long
    Map<String, List<Product>> getCategoryMappedProducts();
    List<ProductCategory> listProductCategories();

    // ADDED: Transactional stock update method (Crucial for e-commerce logic)
    void updateStock(Long productId, int quantityChange);
}