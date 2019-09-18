package ua.yakovenko.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.yakovenko.model.domain.Exhibition;
import ua.yakovenko.model.domain.User;
import ua.yakovenko.model.repository.ExhibitionRepository;

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

    public Page<Exhibition> findByAuthor(User user, Pageable pageable) {
        return exhibitionRepository.findByAuthor(user, pageable);
    }
}
