package ru.gb.springdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimerAspect {

    @Pointcut("within(@ru.gb.springdemo.aspect.Timer *)")
    public void beansAnnotatedWith() {}

    @Pointcut("@annotation(ru.gb.springdemo.aspect.Timer)")
    public void methodsAnnotatedWith() {}

    @Around("beansAnnotatedWith() || methodsAnnotatedWith()")
    public Object loggableAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        System.out.println("Метод " + joinPoint.getSignature() + " выполнялся " +
                (endTime - startTime) + " миллисекунд.");

        return result;
    }
}
