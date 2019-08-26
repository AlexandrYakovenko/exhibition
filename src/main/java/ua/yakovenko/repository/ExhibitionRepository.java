package ua.yakovenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.yakovenko.domain.Exhibition;

import java.util.List;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
    List<Exhibition> findByShowroom(String showroom);
}
