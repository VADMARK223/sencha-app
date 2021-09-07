package senchabackend.controller;

import senchabackend.entity.PersonnelEntity;
import senchabackend.entity.UserEntity;
import senchabackend.model.ResultsResponse;
import senchabackend.repository.PersonnelRepository;
import senchabackend.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultsResponse<PersonnelEntity> getPersonnel() {
        List<PersonnelEntity> personnelList = personnelRepository.findAll();
        ResultsResponse<PersonnelEntity> resultsResponse = new ResultsResponse<>();
        resultsResponse.getResults().addAll(personnelList);
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
