package com.rent_a_car.Aop;

import com.rent_a_car.Controllers.LogController;
import com.rent_a_car.Model.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class RentAop {
    @Autowired
    private LogController logController;

    @After("execution(* com.rent_a_car.Controllers.RentController.findAll(..))")
    public void findAll(JoinPoint joinPoint ){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("findAll Rent");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.RentController.findById(..))")
    public void findById(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("findById Rent");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.RentController.create(..)) ")
    public void create(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("create Rent");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.RentController.delete(..))")
    public void delete(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("delete Rent");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.RentController.update(..))")
    public void update(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("update Rent");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.RentController.getRentUsername(..))")
    public void getRentUsername(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("getRentUsername Rent");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };


}

