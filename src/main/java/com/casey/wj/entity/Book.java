package com.casey.wj.entity;
/*
 * @author CaseyL
 * @date 2022/9/29 14:29
 * */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name = "book")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String cover;
    private String title;
    private String author;
    private String date;
    private String press;
    private String abs;

    @ManyToOne
    @JoinColumn(name = "cid")
    private Category category;


}
