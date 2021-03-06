package senchaserver.service;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import senchaserver.entity.PersonnelEntity;
import senchaserver.model.FilterParam;
import senchaserver.model.SortParam;
import senchaserver.repository.PersonnelRepository;
import senchaserver.specification.PersonnelSpecification;

import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * @author Markitanov Vadim
 * @since 12.09.2021
 */
@Service
@CacheConfig(cacheNames = "personnelCache")
public class PersonnelService {
    private ObjectMapper objectMapper;
    private PersonnelRepository personnelRepository;

    @SneakyThrows
    @Cacheable
    public Page<PersonnelEntity> getPersonnelList(Integer page, Integer limit, String sort, String filter) {
        System.out.println("Page: " + page);
        System.out.println("Limit: " + limit);
        System.out.println("Sort: " + sort);
        System.out.println("Filter: " + filter);

        // Sort
        List<SortParam> sortParamList = new ArrayList<>();
        if (sort != null) {
            sortParamList = Arrays.asList(objectMapper.readValue(sort, SortParam[].class));
        }

        List<Sort.Order> orders = new ArrayList<>();
        for (SortParam sortParam : sortParamList) {
            Sort.Order order = new Sort.Order(Sort.Direction.fromString(sortParam.getDirection()), sortParam.getProperty());
            orders.add(order);
        }

        // Filter
        List<FilterParam> filterParamList = new ArrayList<>();
        if (filter != null) {
            filterParamList = Arrays.asList(objectMapper.readValue(filter, FilterParam[].class));
        }

        PersonnelSpecification personnelSpecification = new PersonnelSpecification(filterParamList);
        Specification<PersonnelEntity> specification = Specification.where(personnelSpecification);
        return personnelRepository.findAll(specification, PageRequest.of(page - 1, limit, Sort.by(orders)));
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    }

    @Autowired
    public void setPersonnelRepository(PersonnelRepository personnelRepository) {
        this.personnelRepository = personnelRepository;
    }

    //    @CachePut(value="personnelCache", key = "#source.lastname")
    public void updatePersonnel(PersonnelEntity source) {
        Optional<PersonnelEntity> targetOptional = personnelRepository.findById(source.id);
        if (!targetOptional.isPresent()) {
            System.err.println("Error: User (" + source.id + ") not exists!");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User (" + source.id + ") not exists!");
        }
        PersonnelEntity target = targetOptional.get();
        BeanUtils.copyProperties(source, target, getNullProperties(source));
        personnelRepository.save(target);
    }

    private String[] getNullProperties(PersonnelEntity source) {
        final BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = beanWrapper.getPropertyValue(propertyName);
            if (propertyValue == null) {
                emptyNames.add(propertyName);
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
