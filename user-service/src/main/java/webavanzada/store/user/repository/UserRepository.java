package webavanzada.store.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webavanzada.store.user.repository.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByNumberID(String numberID);
    public List<User> findByLastName(String lastName);
}
