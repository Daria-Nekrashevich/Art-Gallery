package nekrashevich.artgallery.service;

import nekrashevich.artgallery.model.User;
import nekrashevich.artgallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Предполагается, что у вас есть репозиторий для работы с пользователями

    public User getUserById(UUID userId) {
        // Ищем пользователя по его айди
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null); // Возвращаем найденного пользователя или null, если пользователь не найден
    }
}

