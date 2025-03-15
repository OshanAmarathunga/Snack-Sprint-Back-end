package com.oshan.snack.sprint.service;

import com.oshan.snack.sprint.models.Cart;
import com.oshan.snack.sprint.models.CartItem;
import com.oshan.snack.sprint.request.AddCartItemRequest;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception;
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;
    public Cart removeItemFromCart( Long cartItem, String jwt) throws Exception;
    public Long calculateCartTotals(Cart cart) throws Exception;
    public Cart findCartById(Long id) throws Exception;
    public Cart findCartByUserId(Long userId) throws Exception;
    public Cart clearCart(String jwt) throws Exception;

}
