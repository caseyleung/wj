package com.casey.wj.dao;

import com.casey.wj.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,Integer> {
    // 这个 DAO 不需要额外构造的方法，JPA 提供的默认方法就够用了。
}
