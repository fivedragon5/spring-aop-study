package study.proxyfactory.common.advice;

import org.aopalliance.intercept.MethodInterceptor;

public class TimeAdvice implements MethodInterceptor {
    @Override
    public Object invoke(org.aopalliance.intercept.MethodInvocation invocation) throws Throwable {
        System.out.println("Time Proxy 실행");
        long start = System.currentTimeMillis();

        Object result = invocation.proceed();

        long end = System.currentTimeMillis();
        System.out.println("Method " + invocation.getMethod().getName() + " executed in " + (end - start) + " ms");
        System.out.println("Time Proxy 종료");
        return result;
    }
}
