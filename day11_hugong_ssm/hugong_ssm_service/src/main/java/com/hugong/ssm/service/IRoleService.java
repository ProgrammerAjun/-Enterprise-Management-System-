package com.hugong.ssm.service;

import com.hugong.ssm.domain.Permission;
import com.hugong.ssm.domain.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> findAll(int page,int size) throws Exception;

    void save(Role role) throws Exception;

    Role findById(String roleId) throws Exception;

    List<Permission> findOtherPermission(String roleId) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;
}
