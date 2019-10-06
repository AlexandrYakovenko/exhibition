package ua.yakovenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.yakovenko.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
