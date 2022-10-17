package com.casey.wj.dao;
/*
 * @author CaseyL
 * @date 2022/10/3 15:52
 * */

import com.casey.wj.entity.AdminUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminUserRoleDao extends JpaRepository<AdminUserRole, Integer> {
//    List<AdminUserRole> findAllByUid(int uid);

    List<AdminUserRole> findAllByUid(int uid);

    void deleteAdminUserRoleByUid(int uid);

    void deleteByRid(Integer rid);
}
