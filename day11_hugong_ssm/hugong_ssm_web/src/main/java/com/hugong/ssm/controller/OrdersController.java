package com.hugong.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.hugong.ssm.domain.Orders;
import com.hugong.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    //查询全部订单（未分页）
    /*@RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll();
        mv.addObject("ordersList",ordersList );
        mv.setViewName("orders-list");
        return mv;
    }*/

    //复选框删除订单操作(无法操作的原因是因为有关联表，权限不够)
    @RequestMapping("/delete.do")
    public String batchDeletes(@RequestParam(name = "delitems")String delitems) {
//        String items = request.getParameter("delitems");// System.out.println(items);
        String[] strs = delitems.split(",");

        for (int i = 0; i < strs.length; i++) {
            try {
                String ordersNum = strs[i];
                System.out.printf("订单编号为："+ordersNum);
                //ordersService.delOrdersByNum(ordersNum);
            } catch (Exception e) {
            }
        }
        return "redirect:findAll.do";
    }

    //查询全部订单(page:第几页,size:每页显示几条数据)
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page,size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    //查询订单详情
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String ordersId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        mv.addObject("orders",orders );
        mv.setViewName("orders-show");
        return mv;
    }
}
