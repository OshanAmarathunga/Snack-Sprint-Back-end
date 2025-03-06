package com.oshan.snack.sprint.service.impl;

import com.oshan.snack.sprint.dto.RestourentDto;
import com.oshan.snack.sprint.models.Address;
import com.oshan.snack.sprint.models.Restaurant;
import com.oshan.snack.sprint.models.User;
import com.oshan.snack.sprint.repository.AddressRepository;
import com.oshan.snack.sprint.repository.RestaurantRepository;
import com.oshan.snack.sprint.request.CreateRestaurantRequest;
import com.oshan.snack.sprint.service.RestaurantService;
import com.oshan.snack.sprint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;


    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
        Address address =addressRepository.save(req.getAddress());
        Restaurant restaurant=new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisionType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
        Restaurant restaurant=findRestaurantById(restaurantId);
        if (restaurant.getCuisineType()!=null){
            restaurant.setCuisineType(updatedRestaurant.getCuisionType());
        }
        if (restaurant.getDescription()!=null){
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        if (restaurant.getName()!=null){
            restaurant.setName(updatedRestaurant.getName());
        }


        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {

    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return List.of();
    }

    @Override
    public List<Restaurant> searchRestaurant() {
        return List.of();
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        return null;
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        return null;
    }

    @Override
    public RestourentDto addToFavorites(Long restaurantId) throws Exception {
        return null;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        return null;
    }
}
