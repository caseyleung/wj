package com.casey.wj.dao;
/*
 * @author CaseyL
 * @date 2022/9/29 14:40
 * */

import com.casey.wj.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Integer> {
    // 这个 DAO 不需要额外构造的方法，JPA 提供的默认方法就够用了。
}
