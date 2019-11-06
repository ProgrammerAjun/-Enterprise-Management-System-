package com.hugong.ssm.service;

import com.hugong.ssm.domain.Role;
import com.hugong.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService{

    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String userId) throws Exception;

    List<Role> findOtherRole(String userId) throws Exception;

    void addRoleToUser(String userId, String[] roleId);
}
