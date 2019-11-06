package com.hugong.ssm.controller;

import com.hugong.ssm.domain.SysLog;
import com.hugong.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.security.Security;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime;     //开始时间
    private Class clazz;        //访问的类
    private Method method;      //访问的方法

    //前置通知  主要是获取开始时间，执行的类是哪一个，执行的是哪一个方法
    @Before("execution(* com.hugong.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws Exception {
        visitTime = new Date();     //当前时间就是要访问的时间
        clazz = jp.getTarget().getClass();      //获取当前访问的类
        String methodName =  jp.getSignature().getName();        //获取访问方法的名字
        Object[] args = jp.getArgs();       //获取访问的方法的参数

        //获取具体执行方法的Method
        if(args.length == 0 || args == null) {
            //如果方法没有参数的话，直接进行获取方法
            method = clazz.getMethod(methodName);
        } else {
            //如果有参数，就将args中所有元素遍历，获取对应的Class,装入到一个Class[]
            Class[] classArgs = new Class[args.length];
            for(int i = 0;i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);
        }
    }


    //后置通知
    @After("execution(* com.hugong.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        //获取访问的时长
        long time = new Date().getTime() - visitTime.getTime();

        //获取url
        String url = "";
        if(clazz != null && method != null && clazz != LogAop.class) {
            //1.获取类上的@RequestMapping("/orders")
            RequestMapping classAnnotations = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotations != null) {
                String[] classValue = classAnnotations.value();
                //2.获取方法上的@RequestMapping("/findAll.do")
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];
                }
            }
        }

        //获取访问的ip
        String ip = request.getRemoteAddr();

        //获取当前操作的用户(获取用户名即可)
        //方法一：直接通过request对象来进行获取
//        User user = (User) request.getAttribute("SPRING_SECURITY_CONTEXT");
//        String username = user.getUsername();
        //方法二：可以通过securityContext获取(springSecurity中方法)
        SecurityContext context = SecurityContextHolder.getContext();   //从上下问中获取到了当前登录的用户
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();


        //将日志相关信息封装到SysLog对象中
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);
        sysLog.setIp(ip);
        sysLog.setMethod("[类名] "+clazz.getName()+"[方法名] "+method.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);

        //调用Service方法完成操作
        //判断：不要将自己查询日志的方法sysLog/findAll.do也存入到数据库中
        if(url == "/sysLog/findAll.do" || clazz.getName() == "com.hugong.ssm.controller.SysLogController") {
            return;
        } else {
            sysLogService.save(sysLog);
        }
    }

}
