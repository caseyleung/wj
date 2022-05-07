package com.casey.wj.service;

import com.casey.wj.dao.CategoryDao;
import com.casey.wj.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;

//    这里对查询的结果做了个排序以及条件判断。
    public List<Category> list() {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return categoryDao.findAll(sort);
    }

    public Category get(int id) {
        Category c = categoryDao.findById(id).orElse(null);
        return c;
    }

}
