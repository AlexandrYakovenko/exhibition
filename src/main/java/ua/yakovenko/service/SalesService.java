package ua.yakovenko.service;

import org.springframework.stereotype.Service;
import ua.yakovenko.domain.Exhibition;
import ua.yakovenko.domain.User;
import ua.yakovenko.repository.ExhibitionRepository;
import ua.yakovenko.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public Optional<Exhibition> findById(Long id) {
       return exhibitionRepository.findById(id);
    }

    public void addTicket(User user, Long salesId) throws Exception {
//        List<Exhibition> listUserTickets = user.getBoughtTickets();

        Exhibition currentTicket = exhibitionRepository.findById(salesId).get();

        if (!user.getBoughtTickets().contains(currentTicket) && user.getAccountMoney() >= currentTicket.getPrice()) {
            user.getBoughtTickets().add(currentTicket);
            user.setAccountMoney(user.getAccountMoney() - currentTicket.getPrice());
        } else {
            throw new Exception();
        }
    }

    public List<Exhibition> findUserTickets(User user) {
        return user.getBoughtTickets();
    }
}
