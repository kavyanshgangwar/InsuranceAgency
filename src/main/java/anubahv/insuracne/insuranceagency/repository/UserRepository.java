package anubahv.insuracne.insuranceagency.repository;

import anubahv.insuracne.insuranceagency.models.User;

public interface UserRepository {

    public User findByEmail(String email);
    public boolean userExists(String email);
    public void save(User user);

}
