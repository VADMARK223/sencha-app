package senchaserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Markitanov Vadim
 * @since 07.09.2021
 */
@Data
@Entity(name = "docs")
public class DocEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name = "size")
    public String size;

    @ManyToOne
    @ToString.Exclude
    @JsonBackReference
    @JoinColumn(name = "user_id")
    public UserEntity user;
}
