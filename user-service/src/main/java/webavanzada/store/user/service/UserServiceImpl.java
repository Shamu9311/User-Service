package webavanzada.store.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webavanzada.store.user.repository.UserRepository;
import webavanzada.store.user.repository.entity.User;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> findUserAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        User userDB = userRepository.findByNumberID ( user.getNumberID () );
        if (userDB != null){
            return  userDB;
        }
        user.setState("CREATED");
        userDB = userRepository.save ( user );
        return userDB;
    }

    @Override
    public User updateUser(User user) {
        User userDB = getUser(user.getId());
        if (userDB == null){
            return  null;
        }
        userDB.setFirstName(user.getFirstName());
        userDB.setLastName(user.getLastName());
        userDB.setSex(user.getSex());
        userDB.setAge(user.getAge());

        return  userRepository.save(userDB);
    }

    @Override
    public User deleteUser(User user) {
        User userDB = getUser(user.getId());
        if (userDB ==null){
            return  null;
        }
        user.setState("DELETED");
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
