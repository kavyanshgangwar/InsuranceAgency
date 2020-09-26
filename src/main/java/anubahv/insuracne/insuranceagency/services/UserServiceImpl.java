package anubahv.insuracne.insuranceagency.services;

import anubahv.insuracne.insuranceagency.models.User;
import anubahv.insuracne.insuranceagency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("client");
        user.setStatus("verified");
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean userExists(String email) {
        return userRepository.userExists(email);
    }
}
