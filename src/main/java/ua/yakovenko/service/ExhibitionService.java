package ua.yakovenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.yakovenko.domain.Exhibition;
import ua.yakovenko.repository.ExhibitionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExhibitionService {
    @Autowired
    private ExhibitionRepository exhibitionRepository;

    public void save(Exhibition exhibition) {
        exhibitionRepository.save(exhibition);
    }

    public Page<Exhibition> findAll(Pageable pageable) {
        return exhibitionRepository.findAll(pageable);
    }

    public Page<Exhibition> findByShowroom(String showroom, Pageable pageable) {
        return exhibitionRepository.findByShowroom(showroom, pageable);
    }
}
