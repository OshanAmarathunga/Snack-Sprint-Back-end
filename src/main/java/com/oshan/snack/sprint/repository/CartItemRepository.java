package com.oshan.snack.sprint.repository;

import com.oshan.snack.sprint.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

}
