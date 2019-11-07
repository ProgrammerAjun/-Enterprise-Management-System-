package com.hugong.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hugong.ssm.dao.IUserDao;
import com.hugong.ssm.domain.Role;
import com.hugong.ssm.domain.UserInfo;
import com.hugong.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class IUserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    //配置密码加密类
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //查询所有用户
    @Override
    public List<UserInfo> findAll(int page,int size) throws Exception {
        //参数pageNum是页码值，参数pageSize代表每页显示条数
        PageHelper.startPage(page,size );
        List<UserInfo> userInfoList = userDao.findAll();
        return userInfoList;
    }

    //新建保存用户
    @Override
    public void save(UserInfo userInfo) throws Exception {
        //进行密码加密
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    //根据UserId查询用户详情
    @Override
    public UserInfo findById(String userId) throws Exception {
        return userDao.findByID(userId);
    }

    //根据userId查询用户可以添加的其他角色
    @Override
    public List<Role> findOtherRole(String userId) throws Exception {
        return userDao.findOtherRole(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId : roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
            //处理自己的用户对象UserInfo封装成UserDetails（下面的User类是继承UserDetails接口的）因为密码安全问题前面并没有使用密码加密，所以在获取到的password前面添加了{noop}
//            User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthorities(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthorities(userInfo.getRoles()));
        return user;
    }

    //返回一个list集合，集合中装的就是角色描述（权限）
    public List<SimpleGrantedAuthority> getAuthorities(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        for(Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

}
