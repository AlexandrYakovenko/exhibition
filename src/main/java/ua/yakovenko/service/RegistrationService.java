package ua.yakovenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.yakovenko.domain.User;
import ua.yakovenko.repository.UserRepository;

import java.util.Collections;
import java.util.UUID;

import static ua.yakovenko.domain.Role.USER;

@Service
public class RegistrationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

    public boolean addUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setAccountMoney(0L);
        user.setRoles(Collections.singleton(USER));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepository.save(user);

        sendMessage(user);

        return true;
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Sweater. Please, visit next link: http://localhost:8090/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation Code", message);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);
        userRepository.save(user);

        return true;
    }
}
