package com.hugong.ssm.service;

import com.hugong.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {


    List<Orders> findAll(int page,int size) throws Exception;

    Orders findById(String ordersId) throws Exception;
}
