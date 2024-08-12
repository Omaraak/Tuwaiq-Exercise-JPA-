package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public void AddCategory(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public boolean updateCategory(Category category, int id) {
        Category oldCategory = categoryRepository.getById(id);
        if (oldCategory != null) {
            oldCategory.setName(category.getName());
            categoryRepository.save(oldCategory);
            return true;
        }
        return false;
    }

    public boolean deleteCategory(int id) {
        Category category = categoryRepository.getById(id);
        if (category != null) {
            categoryRepository.delete(category);
            return true;
        }
        return false;
    }
}

