package senchaserver.specification;

import org.springframework.data.jpa.domain.Specification;
import senchaserver.entity.PersonnelEntity;
import senchaserver.model.FilterParam;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Markitanov Vadim
 * @since 11.09.2021
 */
public class PersonnelSpecification implements Specification<PersonnelEntity> {
    private final List<FilterParam> filterParams;

    public PersonnelSpecification(List<FilterParam> filterParams) {
        this.filterParams = filterParams;
    }

    @Override
    public Predicate toPredicate(Root<PersonnelEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = buildPredicates(root, criteriaBuilder);
        if (predicates.isEmpty()) {
            return null;
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private List<Predicate> buildPredicates(Root<PersonnelEntity> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        filterParams.forEach(condition -> predicates.add(buildPredicate(condition, root, criteriaBuilder)));
        return predicates;
    }

    private Predicate buildPredicate(FilterParam condition, Root<PersonnelEntity> root, CriteriaBuilder criteriaBuilder) {
        if (condition.operator.equalsIgnoreCase("like")) {
            return criteriaBuilder.like(root.get(condition.property), "%" + condition.value + "%");

        }
        return null;
    }
}
