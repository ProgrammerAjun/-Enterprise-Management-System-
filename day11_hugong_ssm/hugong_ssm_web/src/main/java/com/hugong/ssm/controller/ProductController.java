package com.hugong.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.hugong.ssm.domain.Product;
import com.hugong.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    //新建产品
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }

    //查询订单详情(不可修改信息)
    @RequestMapping("findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String productId) throws Exception{
        ModelAndView mv = new ModelAndView();
        Product product = productService.findById(productId);
        mv.addObject("product",product);
        mv.setViewName("product-show");
        return mv;
    }

    //修改产品信息(第一步：查询产品信息（可修改信息）)
    @RequestMapping("/updateResearch.do")
    public ModelAndView updateProductById(@RequestParam(name = "id",required = true)String productId) throws Exception{
        ModelAndView mv = new ModelAndView();
        Product product = productService.findById(productId);
        mv.addObject("product",product);
        mv.setViewName("product-edit");
        return mv;
    }

    //修改产品信息(第二步：拿到表单提交的信息，进行保存)
    @RequestMapping("/update.do")
    public String update(Product product) throws Exception{
        productService.updateProduct(product);
        return "redirect:findAll.do";
    }

    //删除商品
    @RequestMapping("/delete.do")
    public String batchDeletes(@RequestParam(name = "delitems")String delitems) {
        String[] strs = delitems.split(",");
        for (int i = 0; i < strs.length; i++) {
            try {
                String productId = strs[i];
                System.out.printf("订单编号为："+productId);
                productService.delProduCtById(productId);
            } catch (Exception e) {
            }
        }
        return "redirect:findAll.do";
    }

    //查询所有产品信息
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(ps);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-search-list");
        return mv;
    }

    //搜索框模拟查询信息
    @RequestMapping("/findByCityName.do")
    public ModelAndView findByCityName(@RequestParam(name = "cityName",required = true)String cityName2,
                                       @RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                       @RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        String cityName = new String(cityName2.getBytes("ISO-8859-1"),"utf-8");
        List<Product> productList = productService.findByCityName(cityName,page,size);
        System.out.println(productList);
        PageInfo pageInfo = new PageInfo(productList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("product-search-list");
        return mv;
    }

}
