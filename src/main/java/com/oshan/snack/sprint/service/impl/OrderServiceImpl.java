package com.oshan.snack.sprint.service.impl;

import com.oshan.snack.sprint.models.*;
import com.oshan.snack.sprint.repository.*;
import com.oshan.snack.sprint.request.OrderRequest;
import com.oshan.snack.sprint.service.CartService;
import com.oshan.snack.sprint.service.OrderService;
import com.oshan.snack.sprint.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;


    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {
        Address shippingAddres=order.getDeliveryAddress();
        Address savedAddress=addressRepository.save(shippingAddres);
        if (!user.getAddresses().contains(savedAddress)){
            user.getAddresses().add(savedAddress);
            userRepository.save( user);
        }

        Restaurant restaurant=restaurantService.findRestaurantById(order.getRestaurantId());
        Order createOrder=new Order();
        createOrder.setCustomer(user);
        createOrder.setCreatedAt(new Date());
        createOrder.setOrderStatus("PENDING");
        createOrder.setDeliveryAddress(savedAddress);
        createOrder.setRestaurant(restaurant);

        Cart cart=cartService.findCartByUserId(user.getId());
        List<OrderItem> orderItems=new ArrayList<>();
        for (CartItem cartItem:cart.getItem()){
            OrderItem orderItem=new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            OrderItem savedOrderItem=orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }
        Long totalPrice=cartService.calculateCartTotals(cart);
        createOrder.setItems(orderItems);
        createOrder.setTotalPrice(totalPrice);

        Order saved= orderRepository.save(createOrder);
        restaurant.getOrders().add(saved);
        return createOrder;

    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order=findOrderById(orderId);
        if (orderStatus.equals("OUT_FOR_DELIVERY")
        || orderStatus.equals("DELIVERED")
        || orderStatus.equals("COMPLETED")
        || orderStatus.equals("PENDING")){
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }

        throw new Exception("Please select a valid order status");

    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order=findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUserOrder(Long userId) throws Exception {
        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
       List<Order> orders= orderRepository.findByRestaurantId(restaurantId);
       if (orderStatus!=null){
           orders=orders.stream().filter(order->order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
       }

       return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> optionalOrder=  orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()){
            throw new Exception("Order not found");
        }

        return optionalOrder.get();
    }
}
