package com.urlshortner.repositories;

import com.urlshortner.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //query methods
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsById(Integer id);
}
