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

    public boolean addUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setAccountMoney(0L);
        user.setRoles(Collections.singleton(USER));

        userRepository.save(user);

        return true;
    }
}