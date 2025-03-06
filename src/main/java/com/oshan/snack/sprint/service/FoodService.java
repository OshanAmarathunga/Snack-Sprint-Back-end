package com.oshan.snack.sprint.service;

import com.oshan.snack.sprint.models.Category;
import com.oshan.snack.sprint.models.Food;
import com.oshan.snack.sprint.models.Restaurant;
import com.oshan.snack.sprint.request.CreateFoodRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoodService {
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);
    void deleteFood(Long foodId) throws Exception;
    public List<Food> getRestaurantsFood(Long restaurantId,
                                         boolean isVegetarian,
                                         boolean isNonveg,
                                         boolean isSeasonal,
                                         String foodCategory);

    public List<Food> searchFood(String keyword);
    public Food findFoodById(Long foodId) throws Exception;
    public Food updateAvailabilityStatus(Long foodId) throws Exception;
}
