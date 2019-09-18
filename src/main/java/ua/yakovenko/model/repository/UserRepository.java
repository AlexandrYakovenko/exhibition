package ua.yakovenko.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.yakovenko.model.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
