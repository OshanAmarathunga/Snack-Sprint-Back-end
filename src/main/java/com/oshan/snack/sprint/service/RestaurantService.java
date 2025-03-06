package com.oshan.snack.sprint.service;

import com.oshan.snack.sprint.dto.RestourentDto;
import com.oshan.snack.sprint.models.Restaurant;
import com.oshan.snack.sprint.models.User;
import com.oshan.snack.sprint.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);
    public Restaurant updateRestaurant(Long restaurantId,CreateRestaurantRequest updatedRequest) throws Exception;
    public void deleteRestaurant(Long restaurantId) throws Exception;
    public List<Restaurant> getAllRestaurant();
    public List<Restaurant> searchRestaurant(String keyword);
    public Restaurant findRestaurantById(Long id) throws Exception;
    public Restaurant getRestaurantByUserId(Long userId) throws Exception;
    public RestourentDto addToFavorites(Long restaurantId) throws Exception;
    public Restaurant updateRestaurantStatus(Long id) throws Exception;
}
