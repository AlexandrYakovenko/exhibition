package ua.yakovenko.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Optional<List<Exhibition>> findAll() {
        return Optional.of(exhibitionRepository.findAll());
    }

    public Optional<List<Exhibition>> findByShowroom(String showroom) {
        return Optional.of(exhibitionRepository.findByShowroom(showroom));
    }
}
