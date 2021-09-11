package senchaserver.repository;

import senchaserver.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Markitanov Vadim
 * @since 07.09.2021
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    List<UserEntity> findAll();
    UserEntity findAllByUsernameAndPassword(String username, String password);
}
