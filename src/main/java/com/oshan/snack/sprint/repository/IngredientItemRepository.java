package com.oshan.snack.sprint.repository;

import com.oshan.snack.sprint.models.IngredientsItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem ,Long> {

}
