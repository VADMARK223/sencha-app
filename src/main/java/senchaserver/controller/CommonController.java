package senchaserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import senchaserver.entity.PersonnelEntity;
import senchaserver.entity.UserEntity;
import senchaserver.model.FilterParam;
import senchaserver.model.ResultsResponse;
import senchaserver.model.SortParam;
import senchaserver.repository.PersonnelRepository;
import senchaserver.repository.UserRepository;
import senchaserver.specification.PersonnelSpecification;

import javax.transaction.Transactional;
import java.beans.PropertyDescriptor;
import java.util.*;

@RestController
public class CommonController {
    private ObjectMapper objectMapper;
    private UserRepository userRepository;
    private PersonnelRepository personnelRepository;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<String> getAuthenticate(@RequestBody MultiValueMap<String, String> multiValueMap) {
        UserEntity user = userRepository.findAllByUsernameAndPassword(multiValueMap.get("username").get(0), multiValueMap.get("password").get(0));

        if (user != null) {
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>("User not valid!", HttpStatus.UNAUTHORIZED);
    }

    @Transactional
    @GetMapping(value = "/user")
    public ResultsResponse<UserEntity> getUsers() {
        List<UserEntity> users = userRepository.findAll();
        System.out.println("users " + users);

        ResultsResponse<UserEntity> resultsResponse = new ResultsResponse<>();
        resultsResponse.getResults().addAll(users);
        return resultsResponse;
    }

    @PostMapping("/user")
    public void postUser() {
        System.out.println("Post user.");
    }

    @GetMapping(value = "/personnel")
    public ResultsResponse<PersonnelEntity> getPersonnel(@RequestParam Integer page,
                                                         @RequestParam Integer limit,
                                                         @RequestParam(required = false) String sort,
                                                         @RequestParam(required = false) String filter) throws JsonProcessingException {
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
            Sort.Order order = new Sort.Order(Sort.Direction.fromString(sortParam.direction), sortParam.property);
            orders.add(order);
        }

        // Filter
        List<FilterParam> filterParamList = new ArrayList<>();
        if (filter != null) {
            filterParamList = Arrays.asList(objectMapper.readValue(filter, FilterParam[].class));
        }

        PersonnelSpecification personnelSpecification = new PersonnelSpecification(filterParamList);
        Specification<PersonnelEntity> specification = Specification.where(personnelSpecification);
        Page<PersonnelEntity> personnelEntityPage = personnelRepository.findAll(specification, PageRequest.of(page - 1, limit, Sort.by(orders)));
        ResultsResponse<PersonnelEntity> resultsResponse = new ResultsResponse<>();
        resultsResponse.getResults().addAll(personnelEntityPage.getContent());
        resultsResponse.setCount(personnelEntityPage.getTotalElements());
        return resultsResponse;
    }

    @PostMapping(value = "/personnel")
    public ResponseEntity<String> postPersonnel(@RequestBody PersonnelEntity source) {
        Optional<PersonnelEntity> targetOptional = personnelRepository.findById(source.id);
        if (!targetOptional.isPresent()) {
            System.err.println("Error: User (" + source.id + ") not exists!");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User (" + source.id + ") not exists!");
        }
        PersonnelEntity target = targetOptional.get();
        BeanUtils.copyProperties(source, target, getNullProperties(source));
        personnelRepository.save(target);

//        return ResponseEntity.ok().build();
        return new ResponseEntity<>(HttpStatus.OK);

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

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPersonnelRepository(PersonnelRepository personnelRepository) {
        this.personnelRepository = personnelRepository;
    }
}
