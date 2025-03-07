package com.oshan.snack.sprint.service;

import com.oshan.snack.sprint.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public Category createCategory(String name,Long userId);
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception;
    public Category findCategoryById(Long id) throws Exception;
}
