package ua.yakovenko.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.yakovenko.domain.Exhibition;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
    Page<Exhibition> findAll(Pageable pageable);

    Page<Exhibition> findByShowroom(String showroom, Pageable pageable);
}
