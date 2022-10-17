package com.casey.wj.dao;
/*
 * @author CaseyL
 * @date 2022/9/29 14:40
 * */

import com.casey.wj.entity.Book;
import com.casey.wj.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book, Integer> {
    List<Book> findAllByCategory(Category category);
    List<Book> findAllByTitleLikeOrAuthorLike(String title, String author);
}
