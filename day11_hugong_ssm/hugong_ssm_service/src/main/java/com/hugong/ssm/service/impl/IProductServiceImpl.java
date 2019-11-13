package com.hugong.ssm.service.impl;


import com.github.pagehelper.PageHelper;
import com.hugong.ssm.dao.IProductDao;
import com.hugong.ssm.domain.Product;
import com.hugong.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 产品信息的业务层接口的实现类
 */
@Service
@Transactional
public class IProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size );
        return productDao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }

    @Override
    public void updateProduct(Product product) throws Exception {
        productDao.updateProduct(product);
    }

    @Override
    public Product findById(String productId) throws Exception {
        return productDao.findById(productId);
    }

    @Override
    public void delProduCtById(String productId) {
        productDao.delProduCtById(productId);
    }

    @Override
    public List<Product> findByCityName(String cityName,int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return productDao.findByCityName(cityName);
    }
}
