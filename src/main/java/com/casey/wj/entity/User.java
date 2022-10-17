package com.casey.wj.entity;
/*
 * @author CaseyL
 * @date 2022/9/28 17:10
 * */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String username;
    private String password;
    private String salt;
    private String name;
    private String phone;
    private String email;
    private String enabled;

    @Transient
    List<String> roles;
}
