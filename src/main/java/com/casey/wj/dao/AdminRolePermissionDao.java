package com.casey.wj.dao;
/*
 * @author CaseyL
 * @date 2022/10/4 9:33
 * */

import com.casey.wj.entity.AdminRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRolePermissionDao extends JpaRepository<AdminRolePermission, Integer> {

    List<AdminRolePermission> findAllByRidIn(List<Integer> rids);

    List<AdminRolePermission> findAllByRid(int rid);

    void deleteByRid(int rid);

}
