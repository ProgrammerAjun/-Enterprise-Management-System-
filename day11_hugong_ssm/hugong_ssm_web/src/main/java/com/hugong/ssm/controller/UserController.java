package com.hugong.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.hugong.ssm.domain.Role;
import com.hugong.ssm.domain.UserInfo;
import com.hugong.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    //给用户添加角色
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,
                                @RequestParam(name = "ids",required = true) String[] roleIds) {
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }

    //根据userId查询用户以及用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据id查询用户
        UserInfo userInfo = userService.findById(userId);
        //根据id查询可以添加的其他角色
        List<Role> roleList = userService.findOtherRole(userId);
        mv.addObject("user", userInfo);
        mv.addObject("roleList", roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    //新建保存用户(用户添加)
    @RequestMapping("save.do")
    @PreAuthorize("authentication.principal.username == 'tom'")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    //查询所有用户
    @RequestMapping("findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfoList = userService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(userInfoList);
        mv.addObject("pageInfo",pageInfo );
        mv.setViewName("user-page-list");
        return mv;
    }

    //根据UserId查询用户详情
    @RequestMapping("findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String UserId) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(UserId);
        mv.addObject("user",userInfo );
        mv.setViewName("user-show");
        return mv;
    }
}
