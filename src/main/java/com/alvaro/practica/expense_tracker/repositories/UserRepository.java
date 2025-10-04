package com.alvaro.practica.expense_tracker.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alvaro.practica.expense_tracker.models.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @EntityGraph(attributePaths = "expenses")
    Optional<User> findOneByUsername(String username);

    boolean existsByUsername(String username);
}
