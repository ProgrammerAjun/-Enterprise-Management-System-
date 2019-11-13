package com.hugong.ssm.service;


import com.hugong.ssm.domain.Product;

import java.util.List;

/**
 * 产品信息的业务层
 */
public interface IProductService {

    public List<Product> findAll(int page,int size) throws Exception;

    void save(Product product) throws Exception;

    void updateProduct(Product product) throws Exception;

    Product findById(String productId) throws Exception;

    void delProduCtById(String productId);

    List<Product> findByCityName(String cityName,int page,int size) throws Exception;
}
