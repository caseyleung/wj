package com.casey.wj.dao;
/*
 * @author CaseyL
 * @date 2022/10/4 9:34
 * */

import com.casey.wj.entity.AdminPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminPermissionDao extends JpaRepository<AdminPermission, Integer> {

}
