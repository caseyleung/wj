package com.casey.wj.dao;
/*
 * @author CaseyL
 * @date 2022/9/28 18:16
 * */


import com.casey.wj.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User getUserByUsername(String username);

    User getByUsernameAndPassword(String username, String password);
}
