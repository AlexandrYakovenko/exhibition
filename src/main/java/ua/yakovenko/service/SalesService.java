package ua.yakovenko.service;

import org.springframework.stereotype.Service;
import ua.yakovenko.domain.User;
import ua.yakovenko.repository.ExhibitionRepository;
import ua.yakovenko.repository.UserRepository;

@Service
public class SalesService {
    private final UserRepository userRepository;
    private final ExhibitionRepository exhibitionRepository;

    public SalesService(UserRepository userRepository, ExhibitionRepository exhibitionRepository) {
        this.userRepository = userRepository;
        this.exhibitionRepository = exhibitionRepository;
    }


    public void addMoney(User user, Long accountMoney) {
        Long userAccountMoney = user.getAccountMoney();
        user.setAccountMoney(userAccountMoney + accountMoney);
    }
}
