package senchaserver.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import senchaserver.entity.PersonnelEntity;
import senchaserver.entity.UserEntity;
import senchaserver.model.ResultsResponse;
import senchaserver.service.PersonnelService;
import senchaserver.service.UserService;
import senchaserver.util.JWTUtil;

@RestController
public class CommonController {
    private UserService userService;
    private PersonnelService personnelService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<String> getAuthenticate(@RequestBody MultiValueMap<String, String> multiValueMap) {
        System.out.println("multiValueMap: " + multiValueMap);

        UserEntity userEntity = userService.authenticate(multiValueMap.get("username").get(0), multiValueMap.get("password").get(0));

        if (userEntity != null) {
            return new ResponseEntity<>(JWTUtil.createToken(userEntity), HttpStatus.OK);
        }
        return new ResponseEntity<>("User not valid!", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<ResultsResponse<UserEntity>> getUsers(@RequestHeader("Authorization") String authorization) {
        String token = getTokenFromRequest(authorization);
        System.out.println("authorization: " + token);
        ResultsResponse<UserEntity> resultsResponse = new ResultsResponse<>();
        try {
            String subjectByToken = JWTUtil.getSubjectByToken(token);
            System.out.println("subjectByToken: " + subjectByToken);
        } catch (JWTVerificationException jwtVerificationException) {
            resultsResponse.setErr("JWT verification error: " + jwtVerificationException.getMessage());
            return new ResponseEntity<>(resultsResponse, HttpStatus.UNAUTHORIZED);
        }

        resultsResponse.getResults().addAll(userService.getUsersList());
        return new ResponseEntity<>(resultsResponse, HttpStatus.OK);
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

    private String getTokenFromRequest(String bearer) {
        if (StringUtils.hasText(bearer) && bearer.startsWith(JWTUtil.BEARER_)) {
            return bearer.substring(7);
        }

        return null;
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
