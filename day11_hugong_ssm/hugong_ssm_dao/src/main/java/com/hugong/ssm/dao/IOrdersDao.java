package com.hugong.ssm.dao;

import com.hugong.ssm.domain.Member;
import com.hugong.ssm.domain.Orders;
import com.hugong.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 订单的持久层接口
 */
public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",javaType = Product.class,one = @One(select =
                    "com.hugong.ssm.dao.IProductDao.findById"))
    })
    List<Orders> findAll() throws Exception;

    //多表操作
    @Select("select * from orders where id = #{ordersId}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select =
                    "com.hugong.ssm.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select =
                    "com.hugong.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,many = @Many(select =
                    "com.hugong.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findByID(String ordersId) throws Exception;
}
