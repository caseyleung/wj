package com.casey.wj.service;
/*
 * @author CaseyL
 * @date 2022/9/28 22:59
 * */

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.casey.wj.dao.AdminRoleDao;
import com.casey.wj.dao.AdminUserRoleDao;
import com.casey.wj.dao.UserDao;
import com.casey.wj.dto.CommonResponseDto;
import com.casey.wj.entity.AdminRole;
import com.casey.wj.entity.AdminUserRole;
import com.casey.wj.entity.User;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final AdminRoleDao roleDao;
    private final AdminUserRoleDao userRoleDao;
    private final AdminRoleService roleService;

    public boolean isExist(String username) {
        User user = userDao.getUserByUsername(username);
        return null!=user;
    }

    public User getUser(String username, String password) {
        return userDao.getByUsernameAndPassword(username, password);
    }

    public CommonResponseDto registerUser(User user) {
        CommonResponseDto responseDto = new CommonResponseDto();
        String username = user.getUsername();
        String password = user.getPassword();

        if (username == null || password == null || "".equals(username) || "".equals(password)) {
            responseDto.setCode(400);
            responseDto.setMessage("用户名和密码不能为空");
            return responseDto;
        }

        boolean exist = isExist(username);
        if (exist) {
            responseDto.setCode(400);
            responseDto.setMessage("用户名已被注册");
            return responseDto;
        }

        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();

        user.setSalt(salt);
        user.setPassword(encodedPassword);
        userDao.save(user);

        responseDto.setMessage("注册成功");
        responseDto.setCode(200);

        return responseDto;
    }

    public User getUserByName(String username) {
        return userDao.getUserByUsername(username);
    }

    public CommonResponseDto getAllUsers() {
        CommonResponseDto responseDto = new CommonResponseDto();
        JSONArray array = new JSONArray();
        List<User> all = userDao.findAll();
        for (User user : all) {
            JSONObject object = new JSONObject();
            object.putOpt("id", user.getId());
            object.putOpt("username", user.getUsername());
            object.putOpt("name", user.getName());
            object.putOpt("phone", user.getPhone());
            object.putOpt("email", user.getEmail());
            boolean enabled = false;
            String userStatus = user.getEnabled();
            if (userStatus != null) {
                enabled = userStatus.equals("Y");
            }
            object.putOpt("enabled", enabled);
            Set<String> roles = roleService.getUserRoleByUserame(user.getUsername());
            object.putOpt("roles", roles);
            array.add(object);
        }
        responseDto.setObject(array);
        return responseDto;
    }


    public CommonResponseDto chagneUserStatus(String username, String userStatus) {
        CommonResponseDto responseDto = new CommonResponseDto();
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            responseDto.setCode(400);
            return responseDto;
        }
        String flag = "true".equals(userStatus)? "Y" : "N";
        user.setEnabled(flag);
        responseDto.setCode(200);
        userDao.save(user);
        return responseDto;
    }

    @Transactional
    public CommonResponseDto updateAddUser(User user) {
        CommonResponseDto responseDto = new CommonResponseDto();
        if (user == null){
            responseDto.setCode(400);
            return responseDto;
        }
        User userInDB = userDao.getUserByUsername(user.getUsername());
        userInDB.setName(user.getName());
        userInDB.setPhone(user.getPhone());
        userInDB.setEmail(user.getEmail());
        userDao.save(userInDB);

        // 先删除用户对应的角色 [uid ---> rid]
        userRoleDao.deleteAdminUserRoleByUid(userInDB.getId());

        // 角色[manager, user] --> roleIds[2, 13]
        List<String> roles = user.getRoles();
        List<Integer> rids = roleDao.findAll().stream().filter(item -> roles.contains(item.getName())).map(AdminRole::getId).collect(Collectors.toList());

        System.out.println(userInDB);
        System.out.println(rids);

        // 添加对应的角色进来
        List<AdminUserRole> roleList = new ArrayList<>();
        rids.forEach(id -> {
            AdminUserRole userRole = new AdminUserRole();
            userRole.setUid(userInDB.getId());
            userRole.setRid(id);
            roleList.add(userRole);
        });
        userRoleDao.saveAll(roleList);
        responseDto.setMessage("ok");
        return responseDto;
    }

    @Transactional
    public CommonResponseDto deleteUser(Integer id) {
        CommonResponseDto responseDto = new CommonResponseDto();
        userDao.deleteById(id);
        // 对应的也要删除user_role关联的， 即用户所带有的角色也要删掉它
        userRoleDao.deleteAdminUserRoleByUid(id);
        responseDto.setMessage("删除成功");
        responseDto.setCode(200);
        return responseDto;
    }

    public CommonResponseDto resetPassword(String username) {
        CommonResponseDto responseDto = new CommonResponseDto();
        User userInDB = userDao.getUserByUsername(username);
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        userInDB.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", "123", salt, times).toString();
        userInDB.setPassword(encodedPassword);
        userDao.save(userInDB);
        responseDto.setCode(200);
        responseDto.setMessage("密码重置为123");
        return responseDto;
    }
}
