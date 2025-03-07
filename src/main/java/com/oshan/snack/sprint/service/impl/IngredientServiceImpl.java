package com.oshan.snack.sprint.service.impl;

import com.oshan.snack.sprint.models.IngredientCategory;
import com.oshan.snack.sprint.models.IngredientsItem;
import com.oshan.snack.sprint.models.Restaurant;
import com.oshan.snack.sprint.repository.IngredientCategoryRepository;
import com.oshan.snack.sprint.repository.IngredientItemRepository;
import com.oshan.snack.sprint.service.IngredientsService;
import com.oshan.snack.sprint.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientsService {

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category=new IngredientCategory();
        category.setRestaurant(restaurant);
        category.setName(name);

        return ingredientCategoryRepository.save(category);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> opt=ingredientCategoryRepository.findById(id);
        if (opt.isEmpty()){
            throw new Exception(
                    "Ingredient category not found"
            );

        }
        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        return List.of();
    }

    @Override
    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        return null;
    }

    @Override
    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {
        return List.of();
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        return null;
    }
}
