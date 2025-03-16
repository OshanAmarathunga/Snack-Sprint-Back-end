package com.oshan.snack.sprint.service;

import com.oshan.snack.sprint.models.Order;
import com.oshan.snack.sprint.models.User;
import com.oshan.snack.sprint.request.OrderRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderService {
    public Order createOrder(OrderRequest order, User user) throws Exception;
    public Order updateOrder(Long orderId,String orderStatus) throws Exception;
    public void cancelOrder(Long orderId) throws Exception;
    public List<Order> getUserOrder(Long userId) throws Exception;
    public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception;
}
