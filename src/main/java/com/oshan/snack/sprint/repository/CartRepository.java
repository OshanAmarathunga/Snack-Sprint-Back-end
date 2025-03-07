package com.oshan.snack.sprint.repository;

import com.oshan.snack.sprint.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {

    public Cart findByCustomerId(Long userId);
}
