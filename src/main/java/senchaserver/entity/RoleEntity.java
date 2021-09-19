package senchaserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Markitanov Vadim
 * @since 19.09.2021
 */
@Data
@Entity
@Table(name = "roles")
@JsonIgnoreProperties(value = {"users"})
public class RoleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;
}
