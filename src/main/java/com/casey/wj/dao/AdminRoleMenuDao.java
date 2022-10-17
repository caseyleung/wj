package com.casey.wj.dao;
/*
 * @author CaseyL
 * @date 2022/10/3 15:52
 * */

import com.casey.wj.entity.AdminRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRoleMenuDao extends JpaRepository<AdminRoleMenu, Integer> {

    //List<AdminRoleMenu> findAllByRidIn(List<Integer> rid);

    List<AdminRoleMenu> findAllByRidIn(List<Integer> rid);
    List<AdminRoleMenu> findAllByRid(int rid);

    void deleteByRid(int id);
}
