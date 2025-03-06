package com.oshan.snack.sprint.service.impl;

import com.oshan.snack.sprint.dto.RestourentDto;
import com.oshan.snack.sprint.models.Address;
import com.oshan.snack.sprint.models.Restaurant;
import com.oshan.snack.sprint.models.User;
import com.oshan.snack.sprint.repository.AddressRepository;
import com.oshan.snack.sprint.repository.RestaurantRepository;
import com.oshan.snack.sprint.repository.UserRepository;
import com.oshan.snack.sprint.request.CreateRestaurantRequest;
import com.oshan.snack.sprint.service.RestaurantService;
import com.oshan.snack.sprint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;


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
        Restaurant restaurant=findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> opt=restaurantRepository.findById(id);
        if (opt.isEmpty()){
            throw new Exception("Restaurant not found with this is");
        }
        return opt.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant=restaurantRepository.findByOwnerId(userId);
        if (restaurant==null){
            throw new Exception("Restaurant not found with owner id"+userId);
        }
        return restaurant;
    }

    @Override
    public RestourentDto addToFavorites(Long restaurantId,User user) throws Exception {
       Restaurant restaurant=findRestaurantById(restaurantId);
       RestourentDto dto=new RestourentDto();
       dto.setDescription(restaurant.getDescription());
       dto.setImages(restaurant.getImages());
       dto.setTitle(restaurant.getName());
       dto.setId(restaurantId);
       if (user.getFavorites().contains(dto)){
           user.getFavorites().remove(dto);
       }
       else {
           user.getFavorites().add(dto);
       }
       userRepository.save(user);
       return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        return null;
    }
}
