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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {
     @Autowired
     private FoodRepository foodRepository;



    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
        Food food=new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setIngrediants(req.getIngredients());
        food.setSeasonal(req.isSeasonal());
        food.setVegirerian(req.isVegetarian());


        Food savedFood= foodRepository.save(food);
        restaurant.getFood().add(savedFood);
        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food=findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);
    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId,
                                         boolean isVegetarian,
                                         boolean isNonveg,
                                         boolean isSeasonal,
                                         String foodCategory) {
       List<Food> foods=foodRepository.findByRestaurantId(restaurantId);
       if (isVegetarian){
           foods=filterByVegiterian(foods,isVegetarian);
       }
       if (isNonveg){
           foods=filterByNonveg(foods,isNonveg);
       }
       if (isSeasonal){
           foods=filterBySeasonal(foods,isSeasonal);
       }
       if (foodCategory!=null && !foodCategory.equals("")){
           foods=filterByCategory(foods,foodCategory);
       }
       return foods;

    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food->{
            if (food.getFoodCategory()!=null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonveg(List<Food> foods, boolean isNonveg) {
        return foods.stream().filter(food->food.isVegirerian()==false).collect(Collectors.toList());
    }

    private List<Food> filterByVegiterian(List<Food> foods, boolean isVegetarian) {
        return foods.stream().filter(food->food.isVegirerian()==isVegetarian).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood=foodRepository.findById(foodId);
        if (optionalFood.isEmpty()){
            throw new Exception("Food not exist...");
        }
        return optionalFood.get();
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {
        Food food=findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
       return foodRepository.save(food);
    }
}
