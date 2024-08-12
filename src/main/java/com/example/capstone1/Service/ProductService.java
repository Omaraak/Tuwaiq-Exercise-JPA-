package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public boolean addProduct(Product product) {
        for (Category category : categoryService.getCategories()){
            if (category.getId() == product.getCategoryID()){
                productRepository.save(product);
                return true;
            }
        }
        return false;
    }

    public List<Product> getProductRepository() {
        return productRepository.findAll();
    }

    public Product getProduct(int id) {
        return productRepository.getById(id);
    }

    public boolean updateProduct(Product product, int id) {
        Product oldProduct = productRepository.getById(id);
        if (oldProduct != null) {
            oldProduct.setName(product.getName());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setCategoryID(product.getCategoryID());
            oldProduct.setBoughtDate(product.getBoughtDate());
            productRepository.save(oldProduct);
            return true;
        }
        return false;
    }

    public boolean deleteProduct(int id) {
        Product product = productRepository.getById(id);
        if (product != null) {
            productRepository.delete(product);
            return true;
        }
        return false;
    }
}
