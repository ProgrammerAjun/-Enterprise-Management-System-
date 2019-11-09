package com.hugong.ssm.dao;

import com.hugong.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品的持久层接口
 */
public interface IProductDao {

    //查询所有的产品信息
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    //保存产品信息
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;

    //根据id查询产品信息
    @Select("select * from product where id = #{id}")
    Product findById(String id) throws Exception;

    //修改产品信息
    @Update("update product set productNum = #{productNum},productName = #{productName},cityName = #{cityName},departureTime = #{departureTime},productPrice = #{productPrice},productDesc = #{productDesc},productStatus = #{productStatus} where id = #{productId}")
    void updateProductById(String productId) throws Exception;

    //删除产品
    @Delete("delete from product where id = #{productId}")
    void delProduCtById(String productId);
}
