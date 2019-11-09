package com.hugong.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.hugong.ssm.dao.IOrdersDao;
import com.hugong.ssm.domain.Orders;
import com.hugong.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
public class IOrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page,int size) throws Exception {
        //参数pageNum是页码值，参数pageSize代表每页显示条数
        PageHelper.startPage(page,size );
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId)throws Exception {
        return ordersDao.findByID(ordersId);
    }

    @Override
    public void delOrdersByNum(String ordersNum) {
        //ordersDao.delOrdersByNum(ordersNum);
    }
}
