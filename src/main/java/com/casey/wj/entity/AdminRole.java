package com.casey.wj.entity;
/*
 * @author CaseyL
 * @date 2022/10/3 15:38
 * */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "admin_role")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String name;
    private String nameZh;
    private String enabled;

    @Transient
    List<String> menus;

    @Transient
    List<String> perms;
}
