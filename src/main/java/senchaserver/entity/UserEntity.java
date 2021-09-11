package senchaserver.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author Markitanov Vadim
 * @since 07.09.2021
 */
@Data
@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;

    @Column(name = "username")
    public String username;

    @Column(name = "password")
    public String password;

    @Column(name = "firstname")
    public String firstname;

    @Column(name = "lastname")
    public String lastname;

    @Column(name = "age")
    public Integer age;

    @Column(name = "gender")
    public String gender;

    @Column(name = "actual")
    public Boolean actual;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    public List<DocEntity> docs;
}