package com.casey.wj.service;
/*
 * @author CaseyL
 * @date 2022/10/3 15:56
 * */

import com.casey.wj.dao.AdminMenuDao;
import com.casey.wj.dto.CommonResponseDto;
import com.casey.wj.entity.AdminMenu;
import com.casey.wj.entity.AdminRoleMenu;
import com.casey.wj.entity.AdminUserRole;
import com.casey.wj.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminMenuService {

    private final UserService userService;
    private final AdminUserRoleService userRoleService;
    private final AdminRoleMenuService roleMenuService;
    private final AdminMenuDao menuDao;



    public CommonResponseDto getMenuByCurrentUser(User user) {
        CommonResponseDto responseDto = new CommonResponseDto();

        // user --> uid  ---> rids  ---> mids --> menus
        // 10000 (contentManager)


        // 由当前用户查询出当前用户对应的角色  (可是每个用户（uid）对应的也就一个rid, 为什么要用list呢？)
        List<Integer> rids = userRoleService.getAllByUid(user.getId()).stream()
                .map(AdminUserRole::getRid).collect(Collectors.toList());

        // 查询出这些角色对应的所有菜单项
        List<Integer> mids = roleMenuService.findAllByRidIn(rids).stream()
                .map(AdminRoleMenu::getMid).collect(Collectors.toList());

        // 根据菜单id去获取所对应的菜单项
        List<AdminMenu> menus = menuDao.findAllById(mids).stream()
                .distinct().collect(Collectors.toList());

        menus.forEach(item -> {
            List<AdminMenu> children = findAllByParentId(item.getId());
            item.setChildren(children);
        });
        menus.removeIf(item -> item.getParentId()!=0);

        responseDto.setObject(menus);
        return responseDto;
    }

    public List<AdminMenu> findAllByParentId(int pid) {
        return menuDao.findAllByParentId(pid);
    }

    // 获取全部菜单（带有层级结构的）
    public List<AdminMenu> getAllMenus() {
        // pid:0 [1.3,4,5]--> children (先找到pid为0的菜单，然后递归找它子节点嵌套进来)
        List<AdminMenu> menus = menuDao.findAllByParentId(0);
        menus.forEach(item -> {
            // 因为只是两层的菜单
            List<AdminMenu> children = findAllByParentId(item.getId());
            item.setChildren(children);
        });

        return menus;
    }
}
