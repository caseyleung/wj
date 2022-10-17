package com.casey.wj.service;
/*
 * @author CaseyL
 * @date 2022/9/29 14:48
 * */

import com.casey.wj.dao.BookDao;
import com.casey.wj.entity.Book;
import com.casey.wj.entity.Category;
import com.casey.wj.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookDao bookDao;
    private final RedisService redisService;

    private final CategoryService categoryService;

    // 改成用dto的形式传输
    public List<Book> getAllBooks() {
        List<Book> bookList = null;
        String key = "books";
        Object bookCache = redisService.get(key);
        
        if (bookCache == null) {
            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            bookList = bookDao.findAll(sort);
            redisService.set(key,bookList);
        } else {
//            bookList = CastUtils.objectConvertToList(bookCache, Book.class);
            bookList = (List<Book>) bookCache;
        }
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject + "is remembered: " + subject.isRemembered());
        System.out.println(subject + "is authenticated: " + subject.isAuthenticated());
        return bookList;
    }

    public void addOrUpdate(Book book) {
        bookDao.save(book);
    }

    public void deleteById(int id) {
        bookDao.deleteById(id);
    }

    public List<Book> findAllByCategory(int cid) {
        Category category = categoryService.getCategory(cid);
        return bookDao.findAllByCategory(category);
    }

    public List<Book> findAllByTitleLikeOrAuthorLike(String keyword) {
        return bookDao.findAllByTitleLikeOrAuthorLike("%" + keyword + "%", "%" + keyword + "%");
    }



}
