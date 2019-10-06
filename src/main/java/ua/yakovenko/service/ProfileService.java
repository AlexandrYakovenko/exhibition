package ua.yakovenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.yakovenko.domain.entity.User;
import ua.yakovenko.repository.UserRepository;

@Service
public class ProfileService {
    @Autowired
    private UserRepository userRepository;

    //TODO сделать так чтоб нельзя было менять Ник не подтвердив пароль
    public void updateProfile(User user, String username, String password) {
        String oldUsername = user.getUsername();

        boolean isUsernameChanged = (username != null && !username.equals(oldUsername))
                || (oldUsername != null && !oldUsername.equals(username));

        if (isUsernameChanged) {
            user.setUsername(username);
        }

        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }

        userRepository.save(user);
    }
}
