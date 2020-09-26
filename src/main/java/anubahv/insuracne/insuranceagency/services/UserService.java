package anubahv.insuracne.insuranceagency.services;

import anubahv.insuracne.insuranceagency.models.User;

public interface UserService {
    public void save(User user);
    public User findByUsername(String email);
    public boolean userExists(String email);
}
