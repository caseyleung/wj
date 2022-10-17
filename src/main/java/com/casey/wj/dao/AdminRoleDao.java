package com.casey.wj.dao;
/*
 * @author CaseyL
 * @date 2022/10/3 15:52
 * */

import com.casey.wj.entity.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRoleDao extends JpaRepository<AdminRole, Integer> {
//    List<AdminRole> findRolesByUser(String username);


//    List<AdminRole> findAdminRoleByUser();


    @Override
    Optional<AdminRole> findById(Integer integer);
}
