package webavanzada.store.user.service;

import webavanzada.store.user.repository.entity.User;

import java.util.List;

public interface UserService {
    public List<User> findUserAll();
    public User createUser(User user);
    public User updateUser(User user);
    public User deleteUser(User user);
    public  User getUser(Long id);
}
