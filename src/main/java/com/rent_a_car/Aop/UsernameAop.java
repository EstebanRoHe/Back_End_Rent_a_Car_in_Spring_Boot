package com.rent_a_car.Aop;

import com.rent_a_car.Controllers.LogController;
import com.rent_a_car.Model.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class UsernameAop {
    @Autowired
    private LogController logController;

    @After("execution(* com.rent_a_car.Controllers.UsernameController.findAll(..))")
    public void findAll(JoinPoint joinPoint ){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("findAll Username");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.UsernameController.findById(..))")
    public void findById(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("findById Username");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.UsernameController.createUser(..)) ")
    public void createUser(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("createUser Username");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.UsernameController.delete(..))")
    public void delete(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("delete Username");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.UsernameController.update(..))")
    public void update(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("update Username");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.UsernameController.getUsersByName(..))")
    public void getUsersByName(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("getUsersByName Username");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
}
