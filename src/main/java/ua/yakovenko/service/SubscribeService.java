package ua.yakovenko.service;

import org.springframework.stereotype.Service;
import ua.yakovenko.domain.User;
import ua.yakovenko.repository.UserRepository;

@Service
public class SubscribeService {
    private final UserRepository userRepository;

    public SubscribeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void subscribe(User currentUser, User user) {
        user.getSubscribers().add(currentUser);

        userRepository.save(user);
    }

    public void unsubscribe(User currentUser, User user) {
        user.getSubscribers().remove(currentUser);

        userRepository.save(user);
    }
}
