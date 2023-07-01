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
public class TypeCarAop {
    @Autowired
    private LogController logController;

    @After("execution(* com.rent_a_car.Controllers.TypeCarController.findAll(..))")
    public void findAll(JoinPoint joinPoint ){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("findAll TypeCar");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.TypeCarController.findById(..))")
    public void findById(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("findById TypeCar");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.TypeCarController.create(..)) ")
    public void create(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("create TypeCar");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.TypeCarController.delete(..))")
    public void delete(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("delete TypeCar");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.TypeCarController.update(..))")
    public void update(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("update TypeCar");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.TypeCarController.getTypeCarDescription(..))")
    public void getTypeCarDescription(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("getTypeCarDescription TypeCar");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
}
