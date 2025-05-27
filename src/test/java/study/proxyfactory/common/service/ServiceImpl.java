package study.proxyfactory.common.service;

public class ServiceImpl implements ServiceInterface {
    @Override
    public void call() {
        System.out.println("ServiceImpl 호출");
    }
}
