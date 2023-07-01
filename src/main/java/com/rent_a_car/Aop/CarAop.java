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
public class CarAop {
    @Autowired
    private LogController logController;

    @After("execution(* com.rent_a_car.Controllers.CarController.findAll(..))")
    public void findAll(JoinPoint joinPoint ){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("findAll Car");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.CarController.findById(..))")
    public void findById(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("findById Car");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if(username == null){
            username = "Visitante";
        }
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.CarController.create(..)) ")
    public void create(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("create Car");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.CarController.delete(..))")
    public void delete(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("delete Car");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.CarController.update(..))")
    public void update(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("update Car");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
    @After("execution(* com.rent_a_car.Controllers.CarController.getCarDescription(..))")
    public void getCarDescription(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("getCarDescription Car");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };

    @After("execution(* com.rent_a_car.Controllers.CarController.getCarLicencePlate(..))")
    public void getCarLicencePlate(JoinPoint joinPoint){
        Log lo = new Log();
        lo.setFecha(new Date());
        lo.setMetodo("getCarLicencePlate Car");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        lo.setUsuario(username);
        logController.create(lo);
    };
}
