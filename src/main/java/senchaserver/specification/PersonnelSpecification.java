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

    @SuppressWarnings("unchecked")
    private Predicate buildPredicate(FilterParam condition, Root<PersonnelEntity> root, CriteriaBuilder criteriaBuilder) {
        switch (condition.getOperator()) {
            case LIKE:
                return criteriaBuilder.like(root.get(condition.property), "%" + condition.value + "%");
            case IN:
                System.out.println("condition: " + condition.value);
                System.out.println("fieldType: " + root.get(condition.property).getJavaType());

                Class<?> fieldType = root.get(condition.property).getJavaType();
                return criteriaBuilder.in(root.get(condition.property)).value(castToRequiredType(fieldType, (List<String>) condition.value));
            default:
                throw new RuntimeException("Operation not supported yet.");
        }
    }

    private Object castToRequiredType(Class<?> fieldType, List<String> values) {
        List<Object> result = new ArrayList<>();
        for (String value : values) {
            result.add(castToRequiredType(fieldType, value));
        }
        return result;
    }

    private Object castToRequiredType(Class<?> fieldType, String value) {
        if (fieldType.isAssignableFrom(String.class)) {
            return value;
        } else if (fieldType.isAssignableFrom(Boolean.class)) {
            return Boolean.valueOf(value);
        } else if (fieldType.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(value);
        } else {
            throw new RuntimeException("Field type '" + fieldType + "' not supported yet.");
        }
    }
}
