package senchaserver.repository;

import org.springframework.data.domain.Pageable;
import senchaserver.entity.PersonnelEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Markitanov Vadim
 * @since 07.09.2021
 */
@Repository
public interface PersonnelRepository extends CrudRepository<PersonnelEntity, Long> {
    List<PersonnelEntity> findAll(Pageable pageable);
}