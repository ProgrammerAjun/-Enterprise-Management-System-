package com.hugong.ssm.service;

import com.hugong.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {

    public List<Permission> findAll(int page,int size) throws Exception;

    void save(Permission permission) throws Exception;

    Permission findById(String permissionId) throws Exception;
}
