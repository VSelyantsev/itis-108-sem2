package ru.itis.kpfu.selyantsev.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.selyantsev.aspect.constants.Message;
import ru.itis.kpfu.selyantsev.model.LogEntity;
import ru.itis.kpfu.selyantsev.repository.LogRepository;

import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class SystemArchitecture {

    private final LogRepository logRepository;

    @Pointcut("@annotation(ru.itis.kpfu.selyantsev.aspect.annotation.CollectData)")
    public void onAllMethods() { }


    @Around("onAllMethods()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        // Getting user current email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();

        Object result = joinPoint.proceed();


        log.info(Message.AFTER, email, methodName);

        //creating and saving logEntity for collecting data
        LogEntity logEntity = LogEntity.builder()
                .timestamp(LocalDateTime.now())
                .userEmail(email)
                .methodName(methodName)
                .severity("INFO")
                .build();
        logRepository.save(logEntity);
        return result;
    }
}
