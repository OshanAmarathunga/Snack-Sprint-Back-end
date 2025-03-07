package com.oshan.snack.sprint.repository;

import com.oshan.snack.sprint.models.IngredientsItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem ,Long> {
    List<IngredientsItem> findByRestaurantId(Long id);
}
