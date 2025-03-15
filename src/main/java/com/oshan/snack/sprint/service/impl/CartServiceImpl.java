package com.oshan.snack.sprint.service.impl;

import com.oshan.snack.sprint.models.Cart;
import com.oshan.snack.sprint.models.CartItem;
import com.oshan.snack.sprint.models.Food;
import com.oshan.snack.sprint.models.User;
import com.oshan.snack.sprint.repository.CartItemRepository;
import com.oshan.snack.sprint.repository.CartRepository;
import com.oshan.snack.sprint.repository.FoodRepository;
import com.oshan.snack.sprint.request.AddCartItemRequest;
import com.oshan.snack.sprint.service.CartService;
import com.oshan.snack.sprint.service.FoodService;
import com.oshan.snack.sprint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodService foodService;


    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Food food=foodService.findFoodById(req.getFoodId());
        Cart cart=cartRepository.findByCustomerId(user.getId());

        for (CartItem cartItem:cart.getItem()){
            if (cartItem.getFood().equals(food)){
                int newQuantity=cartItem.getQuantity()+req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(),newQuantity);
            }
        }

        CartItem newCartItem=new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setIngredients(req.getIngredients());
        newCartItem.setTotalPrice(req.getQuantity()*food.getPrice());

        CartItem savedCartItem=cartItemRepository.save(newCartItem);
        cart.getItem().add(savedCartItem);
        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItemOptional=cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isEmpty()){
            throw new Exception("Cart item not found");
        }
        CartItem item=cartItemOptional.get();
        item.setQuantity(quantity);

        item.setTotalPrice(item.getFood().getPrice()*quantity);
        return cartItemRepository.save(item);

    }

    @Override
    public Cart removeItemFromCart(Long cartItem, String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Cart cart=cartRepository.findByCustomerId(user.getId());

        Optional<CartItem> cartItemOptional=cartItemRepository.findById(cartItem);
        if (cartItemOptional.isEmpty()){
            throw new Exception("Cart item not found");
        }

        CartItem item=cartItemOptional.get();
        cart.getItem().remove(item);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        Long total=0L;
        for (CartItem cartItem: cart.getItem()){
            total+=cartItem.getFood().getPrice()*cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        return null;
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        return null;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        return null;
    }
}
