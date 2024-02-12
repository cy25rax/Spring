package ru.gb.springdemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class RecoverExceptionAspect {

    @Around("@annotation(ru.gb.springdemo.aspect.RecoverException)")
    public Object loggableAspect(ProceedingJoinPoint joinPoint) throws Throwable {

        List<Class<? extends RuntimeException>> classListExceptions = List.of(((MethodSignature) joinPoint.getSignature())
                .getMethod()
                .getAnnotation(RecoverException.class)
                .noRecoverFor());

        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Exception e){
            if (classListExceptions.contains(e.getClass())) {
                System.out.println("exception in class list noRecoverfor");
            } else {
                System.out.println(e.getClass());
                System.out.println(e.getMessage());
                throw new Exception(e.getMessage());
            }
        }

        return proceed;
    }
}
