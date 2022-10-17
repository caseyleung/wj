package com.casey.wj.service;
/*
 * @author CaseyL
 * @date 2022/9/29 14:44
 * */

import com.casey.wj.dao.CategoryDao;
import com.casey.wj.entity.Category;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryDao categoryDao;

    public List<Category> categoryList() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return categoryDao.findAll(sort);
    }

    public Category getCategory(int id) {
        Category category = categoryDao.findById(id).orElse(null);
        return category;
    }
}
