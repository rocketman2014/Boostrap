package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsersDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UsersDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found");
        return new User(user.get());
    }

    public User findByUserId(Long id) {
        Optional<User> userDb = userRepository.findById(id);
        return userDb.orElse(new User());
    }
    public List<User> allUser() {
        return userRepository.findAll();
    }
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    @Transactional
    public void updateUser (Long id, User upUser) {
        upUser.setId(id);
        userRepository.save(upUser);

    }
}
