package dev.marvin.serviceImpl;

import dev.marvin.exception.DuplicateResourceException;
import dev.marvin.model.Role;
import dev.marvin.model.User;
import dev.marvin.repository.UserRepository;
import dev.marvin.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean isUserRegistered(String mobile) {
        return userRepository.findByMobile(mobile).isPresent();
    }

    @Override
    @Transactional
    public User create(String username, String password) {
        log.info("Inside create method of UserServiceImpl");
        try {
            User user = User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .role(Role.CUSTOMER)
                    .isDeleted(false)
                    .build();
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateResourceException("username [%s] already taken".formatted(username));
        }
    }

}


