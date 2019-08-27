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

    public List<Exhibition> findAll() {
        return exhibitionRepository.findAll();
    }

    public List<Exhibition> findByShowroom(String showroom) {
        return exhibitionRepository.findByShowroom(showroom);
    }
}
