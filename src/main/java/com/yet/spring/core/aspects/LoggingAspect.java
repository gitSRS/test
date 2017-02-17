package com.yet.spring.core.aspects;

import com.yet.spring.core.logger.FileEventLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AdviceName;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by RStreltsov on 16.02.2017.
 */
@Aspect
// @Component
public class LoggingAspect {

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {}

    @Pointcut("allLogEventMethods() && within(*.*File*Logger)")
    private void logEventInsideFileLoggers() {}

    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("BEFORE: " +
                joinPoint.getTarget().getClass().getSimpleName() + " " +
                joinPoint.getSignature().getName());
        Method m;
        try {
            m = joinPoint.getTarget().getClass().getMethod("methodTest", null);
            //System.out.println(m.getName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

}
