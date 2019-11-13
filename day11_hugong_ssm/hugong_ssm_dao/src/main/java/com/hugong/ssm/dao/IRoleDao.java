package com.hugong.ssm.dao;

import com.hugong.ssm.domain.Permission;
import com.hugong.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    //根据用户id查询出所对应的用户角色
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select =
            "com.hugong.ssm.dao.IPermissionDao.findByRoleId")),
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;

    //根据权限id查询出所对应的角色
    @Select("select * from role where id in (select roleId from role_permission where permissionId = #{permissionId})")
    public List<Role> findRoleByPermissionId(String permissionId) throws Exception;

    @Select("select * from role")
    public List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values (#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    @Select("select * from role where id = #{roleId}")
    Role findById(String roleId) throws Exception;

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermission(String roleId) throws Exception;

    @Insert("insert into role_permission (roleId,permissionId) values (#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId) throws Exception;
}
