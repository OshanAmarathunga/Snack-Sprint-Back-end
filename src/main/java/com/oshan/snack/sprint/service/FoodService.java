package com.oshan.snack.sprint.service;

import com.oshan.snack.sprint.models.Category;
import com.oshan.snack.sprint.models.Food;
import com.oshan.snack.sprint.models.Restaurant;
import org.springframework.stereotype.Service;

@Service
public interface FoodService {
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);
}
