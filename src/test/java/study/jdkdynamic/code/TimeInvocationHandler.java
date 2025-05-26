package study.jdkdynamic.code;

import java.lang.reflect.InvocationHandler;

public class TimeInvocationHandler implements InvocationHandler {
    private final Object target;

    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args) throws Throwable {
        System.out.println("Time Proxy 호출");
        long start = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        long end = System.currentTimeMillis();
        System.out.println("Method " + method.getName() + " executed in " + (end - start) + " ms");
        System.out.println("Time Proxy 종료");
        return result;
    }
}
