package senchaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import senchaserver.entity.PersonnelEntity;
import senchaserver.entity.UserEntity;
import senchaserver.model.ResultsResponse;
import senchaserver.service.PersonnelService;
import senchaserver.service.UserService;

@RestController
public class CommonController {
    private UserService userService;
    private PersonnelService personnelService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<String> getAuthenticate(@RequestBody MultiValueMap<String, String> multiValueMap) {
        if (userService.authenticate(multiValueMap.get("username").get(0), multiValueMap.get("password").get(0))) {
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>("User not valid!", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "/user")
    public ResultsResponse<UserEntity> getUsers() {
        ResultsResponse<UserEntity> resultsResponse = new ResultsResponse<>();
        resultsResponse.getResults().addAll(userService.getUsersList());
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
                                                         @RequestParam(required = false) String filter) {
        ResultsResponse<PersonnelEntity> resultsResponse = new ResultsResponse<>();
        Page<PersonnelEntity> personnelList = personnelService.getPersonnelList(page, limit, sort, filter);
        resultsResponse.getResults().addAll(personnelList.getContent());
        resultsResponse.setCount(personnelList.getTotalElements());
        return resultsResponse;
    }

    @PostMapping(value = "/personnel")
    public ResponseEntity<String> postPersonnel(@RequestBody PersonnelEntity source) {
        personnelService.updatePersonnel(source);
        return new ResponseEntity<>(HttpStatus.OK);

    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPersonnelService(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }
}
