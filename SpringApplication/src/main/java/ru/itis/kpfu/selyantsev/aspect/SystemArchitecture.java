package ru.itis.kpfu.selyantsev.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SystemArchitecture {

    @Pointcut("within(ru.itis.kpfu.selyantsev..*)")
    public void onAllMethods() {
    }

    @Pointcut("@annotation(ru.itis.kpfu.selyantsev.aspect.annotation.Countdown)")
    public void aroundSpecificMethod () {}

}
