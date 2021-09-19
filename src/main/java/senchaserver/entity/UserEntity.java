package senchaserver.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author Markitanov Vadim
 * @since 07.09.2021
 */
@Data
@Entity(name = "users")
public class UserEntity implements Serializable {
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
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public List<DocEntity> docs;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<RoleEntity> roles;
}
