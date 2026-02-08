package xyz.trevvo.auth.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.trevvo.auth.domain.user.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
