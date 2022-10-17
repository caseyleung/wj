package com.casey.wj.dao;
/*
 * @author CaseyL
 * @date 2022/10/3 15:51
 * */

import com.casey.wj.entity.AdminMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminMenuDao extends JpaRepository<AdminMenu, Integer> {
//    List<AdminMenu> findAllByParentId(int pid);

    List<AdminMenu> findAllByParentId(int pid);
}
