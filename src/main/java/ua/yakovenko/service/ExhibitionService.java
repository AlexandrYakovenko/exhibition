package ua.yakovenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.yakovenko.domain.entity.Exhibition;
import ua.yakovenko.domain.entity.User;
import ua.yakovenko.repository.ExhibitionRepository;

@Service
public class ExhibitionService {

    @Autowired
    private ExhibitionRepository exhibitionRepository;

    /**
     * Save entity exhibition un repository
     *
     * @param exhibition
     */
    public void save(Exhibition exhibition) {
        exhibitionRepository.save(exhibition);
    }

    /**
     * @param pageable
     * @return page of exhibitions, default param 10
     */
    public Page<Exhibition> findAll(Pageable pageable) {
        return exhibitionRepository.findAll(pageable);
    }

    /**
     * @param showroom
     * @param pageable
     * @return
     */
    public Page<Exhibition> findByShowroom(String showroom, Pageable pageable) {
        return exhibitionRepository.findByShowroom(showroom, pageable);
    }

    /**
     * @param user
     * @param pageable
     * @return
     */
    public Page<Exhibition> findByAuthor(User user, Pageable pageable) {
        return exhibitionRepository.findByAuthor(user, pageable);
    }

    /**
     * @param id
     */
    public void deleteById(Long id) {
        exhibitionRepository.deleteById(id);
    }

    /**
     * @param id
     * @return
     */
    public Exhibition findById(Long id) {
        return exhibitionRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
