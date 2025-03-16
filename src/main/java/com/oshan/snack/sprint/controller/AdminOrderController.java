package com.oshan.snack.sprint.controller;

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
@RequestMapping("/api/admin")
public class AdminOrderController {
    private OrderService orderService;

    @Autowired
    private UserService userService;



    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(
            @PathVariable Long id,
            @RequestParam(required = false) String orderStatus,
            @RequestHeader("Authorization") String jwt) throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        List<Order> orders =orderService.getRestaurantsOrder(id,orderStatus);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/order/{id}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @PathVariable String orderStatus,
            @RequestHeader("Authorization") String jwt) throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Order orders =orderService.updateOrder(id,orderStatus);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
