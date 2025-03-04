package com.oshan.snack.sprint.repository;

import com.oshan.snack.sprint.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmail(String username);
}
