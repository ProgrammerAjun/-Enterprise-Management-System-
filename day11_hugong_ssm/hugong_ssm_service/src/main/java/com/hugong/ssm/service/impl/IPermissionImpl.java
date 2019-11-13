package com.hugong.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.hugong.ssm.dao.IPermissionDao;
import com.hugong.ssm.domain.Permission;
import com.hugong.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class IPermissionImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size );
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(String permissionId) throws Exception {
        return permissionDao.findById(permissionId);
    }
}
