package senchabackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import senchabackend.entity.PersonnelEntity;
import senchabackend.entity.UserEntity;
import senchabackend.model.ResultsResponse;
import senchabackend.model.SortParam;
import senchabackend.repository.PersonnelRepository;
import senchabackend.repository.UserRepository;

import java.beans.PropertyDescriptor;
import java.util.*;

@RestController
public class CommonController {
    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonnelRepository personnelRepository;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<String> getAuthenticate(@RequestBody MultiValueMap<String, String> multiValueMap) {
        UserEntity user = userRepository.findAllByUsernameAndPassword(multiValueMap.get("username").get(0), multiValueMap.get("password").get(0));

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        if (user != null) {
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>("User not valid!", HttpStatus.UNAUTHORIZED);
    }

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
                                                         @RequestParam(required = false) String sort) throws JsonProcessingException {
        System.out.println("Page: " + page);
        System.out.println("Limit: " + limit);
        System.out.println("Sort: " + sort);

        List<SortParam> sortParamList = new ArrayList<>();
        if (sort != null) {
            sortParamList = Arrays.asList(objectMapper.readValue(sort, SortParam[].class));
        }

        List<Sort.Order> orders = new ArrayList<>();
        for (SortParam sortParam : sortParamList) {
            Sort.Order order = new Sort.Order(Sort.Direction.fromString(sortParam.direction), sortParam.property);
            orders.add(order);
        }

        List<PersonnelEntity> personnelList = personnelRepository.findAll(PageRequest.of(page - 1, limit, Sort.by(orders)));
        ResultsResponse<PersonnelEntity> resultsResponse = new ResultsResponse<>();
        resultsResponse.getResults().addAll(personnelList);
        resultsResponse.setCount(personnelRepository.count());
        return resultsResponse;
    }

    @PostMapping(value = "/personnel")
    public ResponseEntity<String> postPersonnel(@RequestBody PersonnelEntity source) {
//        source.id = 99999L;

        Optional<PersonnelEntity> targetOptional = personnelRepository.findById(source.id);
        if (!targetOptional.isPresent()) {
            System.err.println("Error: User (" + source.id + ") not exists!");
            return new ResponseEntity<>("User (" + source.id + ") not exists!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PersonnelEntity target = targetOptional.get();
        BeanUtils.copyProperties(source, target, getNullProperties(source));
        personnelRepository.save(target);

        return ResponseEntity.ok().build();

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
