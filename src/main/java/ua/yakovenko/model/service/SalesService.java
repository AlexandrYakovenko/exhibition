package ua.yakovenko.model.service;

import org.springframework.stereotype.Service;
import ua.yakovenko.model.domain.Exhibition;
import ua.yakovenko.model.domain.User;
import ua.yakovenko.model.exception.BuyException;
import ua.yakovenko.model.repository.ExhibitionRepository;
import ua.yakovenko.model.repository.UserRepository;

import java.util.List;
import java.util.Optional;

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

    //TODO сделат красиво
    public void addTicket(User user, Long salesId) throws BuyException {
        Exhibition currentTicket = exhibitionRepository.findById(salesId).get();

        if (!user.getBoughtTickets().contains(currentTicket)
                && user.getAccountMoney() >= currentTicket.getPrice()) {
            user.getBoughtTickets().add(currentTicket);

            user.setAccountMoney(
                    user.getAccountMoney() - currentTicket.getPrice());
        } else {
            throw new BuyException();
        }
    }

    public List<Exhibition> findUserTickets(User user) {
        return user.getBoughtTickets();
    }
}