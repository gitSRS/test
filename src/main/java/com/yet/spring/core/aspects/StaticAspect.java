package com.yet.spring.core.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by RStreltsov on 17.02.2017.
 */
@Aspect
public class StaticAspect {

    private Map<Class<?>, Integer> counter = new HashMap();

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {}

    @AfterReturning("allLogEventMethods()")
    //@After("allLogEventMethods()")
    public void count(JoinPoint jp){
        System.out.println("AFTER: " +
                jp.getTarget().getClass().getSimpleName());

        Class<?> cl = jp.getTarget().getClass();

        if(!counter.containsKey(cl)) {
            counter.put(cl, 1);
        } else {
            counter.put(cl, counter.get(cl) + 1);
        }
    }

    public Map<Class<?>, Integer> getCounter() {
        return Collections.unmodifiableMap(counter);
    }

    @AfterReturning("execution(* com.yet.spring.core.App.getStatistic())")
    public void outputLoggingCounter(){
        System.out.println("Loggers statistics. Number of calls: ");
        for (Map.Entry<Class<?>, Integer> entry : counter.entrySet()) {
            System.out.println("    " + entry.getKey().getSimpleName() + ": " + entry.getValue());
        }

    }
}
