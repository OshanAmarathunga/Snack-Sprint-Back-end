package com.oshan.snack.sprint.service.impl;

import com.oshan.snack.sprint.models.Category;
import com.oshan.snack.sprint.models.Food;
import com.oshan.snack.sprint.models.Restaurant;
import com.oshan.snack.sprint.repository.FoodRepository;
import com.oshan.snack.sprint.request.CreateFoodRequest;
import com.oshan.snack.sprint.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FoodServiceImpl implements FoodService {
     @Autowired
     private FoodRepository foodRepository;



    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
        return null;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {

    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonveg, boolean isSeasonal, String foodCategory) {
        return List.of();
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return List.of();
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        return null;
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {
        return null;
    }
}
