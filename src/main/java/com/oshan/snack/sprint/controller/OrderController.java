package com.oshan.snack.sprint.controller;

import com.oshan.snack.sprint.models.CartItem;
import com.oshan.snack.sprint.models.Order;
import com.oshan.snack.sprint.models.User;
import com.oshan.snack.sprint.request.OrderRequest;
import com.oshan.snack.sprint.service.OrderService;
import com.oshan.snack.sprint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest req,
                                             @RequestHeader("Authorization") String jwt) throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Order order=orderService.createOrder(req,user);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(
                                             @RequestHeader("Authorization") String jwt) throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        List<Order> orders =orderService.getUserOrder(user.getId());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
