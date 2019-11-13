package com.hugong.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.hugong.ssm.domain.Permission;
import com.hugong.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(permissionList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-page-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception{
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    //查询资源权限详情
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String permissionId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionService.findById(permissionId);
        mv.addObject("permission",permission );
        mv.setViewName("permission-show");
        return mv;
    }
}
