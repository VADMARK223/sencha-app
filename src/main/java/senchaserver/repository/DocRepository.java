package senchaserver.repository;

import senchaserver.entity.DocEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Markitanov Vadim
 * @since 07.09.2021
 */
@Repository
public interface DocRepository extends CrudRepository<DocEntity, Long> {
}
