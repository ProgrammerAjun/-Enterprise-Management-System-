package com.hugong.ssm.dao;

import com.hugong.ssm.domain.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{roleId})")
    public List<Permission> findByRoleId(String roleId) throws Exception;

    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    @Insert("insert into permission(permissionName,url) values (#{permissionName},#{url})")
    void save(Permission permission) throws Exception;

    //多表操作
    @Select("select * from permission where id = #{permissionId}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "permissionName",column = "permissionName"),
            @Result(property = "url",column = "url"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select =
                    "com.hugong.ssm.dao.IRoleDao.findRoleByPermissionId"))
    })
    Permission findById(String permissionId) throws Exception;
}
