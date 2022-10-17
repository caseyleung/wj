package com.casey.wj.service;
/*
 * @author CaseyL
 * @date 2022/10/3 15:57
 * */


import com.casey.wj.dao.AdminMenuDao;
import com.casey.wj.dao.AdminRoleMenuDao;
import com.casey.wj.entity.AdminMenu;
import com.casey.wj.entity.AdminRoleMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminRoleMenuService {

    private final AdminMenuDao menuDao;
    private final AdminRoleMenuDao roleMenuDao;
    public List<AdminRoleMenu> findAllByRidIn(List<Integer> rids) {
        return roleMenuDao.findAllByRidIn(rids);
    }

    public List<AdminMenu> findAllByRid(int rid) {

        List<Integer> mids = roleMenuDao.findAllByRid(rid).stream()
                .map(AdminRoleMenu::getMid).collect(Collectors.toList());

        return new ArrayList<>(menuDao.findAllById(mids));

    }
}
