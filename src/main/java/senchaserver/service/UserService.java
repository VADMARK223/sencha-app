package senchaserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import senchaserver.entity.UserEntity;
import senchaserver.repository.UserRepository;

import java.util.List;

/**
 * @author Markitanov Vadim
 * @since 12.09.2021
 */

@Service
@CacheConfig(cacheNames = "userCache")
public class UserService {
    private UserRepository userRepository;

    @Cacheable
    public List<UserEntity> getUsersList() {
        return userRepository.findAll();
    }

    public UserEntity authenticate(String username, String password) {
        return userRepository.findAllByUsernameAndPassword(username, password);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
