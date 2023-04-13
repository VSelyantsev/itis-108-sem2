package ru.itis.kpfu.selyantsev.aspect.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;
import ru.itis.kpfu.selyantsev.aspect.SystemArchitecture;
import ru.itis.kpfu.selyantsev.aspect.constants.Message;

@Aspect
@Slf4j
@Configuration
public class ConsoleConfig {

    @Bean
    public SystemArchitecture systemArchitecture() {
        return new SystemArchitecture();
    }

    @Before(value = "ru.itis.kpfu.selyantsev.aspect.SystemArchitecture.onAllMethods()")
    public void beforeOnAllMethods(JoinPoint joinPoint) throws Throwable {
        log.info(
                Message.BEFORE_METHOD,
                joinPoint.getClass(),
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName()
        );
    }

    @After(value = "ru.itis.kpfu.selyantsev.aspect.SystemArchitecture.onAllMethods()")
    public void afterOnAllMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        log.info(
                Message.AFTER_METHOD,
                joinPoint.getSignature().getName(),
                result,
                stopWatch.getTotalTimeMillis()
        );
    }

    @Around("execution(* ru.itis.kpfu.selyantsev.aspect.SystemArchitecture.aroundSpecificMethod())")
    public void aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(Message.START_METHOD, joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        log.info(Message.END_METHOD, joinPoint.getSignature().getName(), result);
    }

    @AfterThrowing(value = "ru.itis.kpfu.selyantsev.aspect.SystemArchitecture.onAllMethods()", throwing = "exception")
    public void exceptionOnAllMethods(JoinPoint joinPoint, Throwable exception) {
        log.error(Message.AFTER_THROWING_ON_ALL_METHODS, joinPoint.getSignature().getName(), exception);
    }
}
