package senchaserver.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import senchaserver.entity.PersonnelEntity;

/**
 * @author Markitanov Vadim
 * @since 07.09.2021
 */
@Repository
public interface PersonnelRepository extends CrudRepository<PersonnelEntity, Long>, JpaSpecificationExecutor<PersonnelEntity> {
}
