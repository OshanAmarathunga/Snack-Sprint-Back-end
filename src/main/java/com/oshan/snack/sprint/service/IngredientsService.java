package com.oshan.snack.sprint.service;

import com.oshan.snack.sprint.models.IngredientCategory;
import com.oshan.snack.sprint.models.IngredientsItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IngredientsService {
    public IngredientCategory createIngredientCategory(String name,Long restaurantId) throws Exception;
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;
    public IngredientsItem createIngredientItem(Long restaurantId,String ingredientName, Long categoryId) throws Exception;
    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId);
    public IngredientsItem updateStock(Long id) throws Exception;
}
