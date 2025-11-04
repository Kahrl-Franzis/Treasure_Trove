package com.asis.serviceimpl;

import com.asis.entity.Product;
import com.asis.service.ProductService;
import com.asis.repository.ProductDataRepository;
import com.asis.model.ProductCategory; // Keep if model exists
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDataRepository productRepository;

    public ProductServiceImpl(ProductDataRepository productRepository) {
        this.productRepository = productRepository;
    }

    // --- Transactional Stock Update (Implied in your project) ---

    @Override
    public void updateStock(Long productId, int quantityChange) {
        // FIX 4: findById now correctly uses a Long ID
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));

        int newStock = product.getStockQuantity() + quantityChange;

        if (newStock < 0) {
            throw new RuntimeException("Insufficient stock for product: " + product.getTitle());
        }

        product.setStockQuantity(newStock);
        productRepository.save(product);
    }

    // --- Interface Methods ---

    @Override
    public List<Product> getAllProducts() {
        // FIX 2 & 3: Ensure this returns a List. If your repository extends JpaRepository, this is fine.
        // If it only extends CrudRepository, uncomment the streaming conversion below:

        // return StreamSupport.stream(productRepository.findAll().spliterator(), false)
        //          .collect(Collectors.toList());

        return productRepository.findAll();
    }

    @Override
    public Product[] getAll() {
        // FIX 2 & 3: Convert the List result to the required Product array.
        List<Product> products = productRepository.findAll();
        return products.toArray(new Product[0]);
    }

    @Override
    public Product get(Long id) { // FIX 4: Changed input type to Long
        // FIX 4: findById is now correctly called with a Long ID
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) { // FIX 4: Changed input type to Long
        // FIX 4: deleteById is now correctly called with a Long ID
        productRepository.deleteById(id);
    }

    // Placeholder implementation for remaining methods
    @Override
    public Map<String, List<Product>> getCategoryMappedProducts() {
        return Collections.emptyMap();
    }

    @Override
    public List<com.asis.model.ProductCategory> listProductCategories() {
        return Collections.emptyList();
    }
}