package anubahv.insuracne.insuranceagency.repository;

import anubahv.insuracne.insuranceagency.models.User;

public interface UserRepository {

    public User findByEmail(String email);
    public boolean userExists(String email);
    public void save(User user);
    public void enableUser(User user);
    public User findByUserId(int id);
    public void addRole(String role,String email);

    void deadUser(int userId);
}
