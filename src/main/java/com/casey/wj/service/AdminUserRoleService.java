package com.casey.wj.service;
/*
 * @author CaseyL
 * @date 2022/10/3 15:57
 * */


import com.casey.wj.dao.AdminUserRoleDao;
import com.casey.wj.entity.AdminUserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserRoleService {

    private final AdminUserRoleDao userRoleDao;
    public List<AdminUserRole> getAllByUid(int uid) {
        List<AdminUserRole> allByUid = userRoleDao.findAllByUid(uid);
        return allByUid;
    }
}
