package senchabackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CommonController {
    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonnelRepository personnelRepository;

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

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(orders));

        List<PersonnelEntity> personnelList = personnelRepository.findAll(pageable);
        ResultsResponse<PersonnelEntity> resultsResponse = new ResultsResponse<>();
        resultsResponse.getResults().addAll(personnelList);
        resultsResponse.setCount(personnelRepository.count());
        return resultsResponse;
    }

    @PostMapping(value = "/personnel")
    public void postPersonnel() {
        System.out.println("Post personnel.");
    }

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
}
