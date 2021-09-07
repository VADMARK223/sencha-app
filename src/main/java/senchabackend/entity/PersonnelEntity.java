package senchabackend.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author Markitanov Vadim
 * @since 07.09.2021
 */
@Data
@Entity(name = "personnel")
public class PersonnelEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name = "email")
    public String email;

    @Column(name = "phone")
    public String phone;

    @Column(name = "address")
    public String address;

    @Column(name = "salary")
    public BigDecimal salary;
}
