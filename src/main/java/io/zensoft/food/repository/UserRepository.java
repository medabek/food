package io.zensoft.food.repository;

import io.zensoft.food.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> getAllByStatus(String status);
    User findByEmail(String email);
    User findById(int id);
}