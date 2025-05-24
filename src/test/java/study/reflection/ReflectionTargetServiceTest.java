package study.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ReflectionTargetServiceTest {

    @Test
    void getClassInformation() {
        // 1. 클래스 객체 얻기
        Class<?> clazz = ReflectionTargetService.class;

        // 2. 클래스 이름 출력
        System.out.println("클래스 이름: " + clazz.getName());
        // 클래스 이름: study.reflection.ReflectionTargetService

        // 3. 필드 목록 출력
        System.out.println("\n[필드 목록]");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println("- " + field.getType().getSimpleName() + " " + field.getName());
        }
        // [필드 목록]
        // - String publicName
        // - int age
        // - int protectedYear

        // 4. 메서드 목록 출력
        System.out.println("\n[메서드 목록]");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println("- " + method.getName());
        }
        // [메서드 목록]
        // - privateMethod
        // - protectedMethod
        // - publicMethod
    }

    @Test
    void getPrivateMethodAndField() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 1. 인스턴스 생성
        ReflectionTargetService target = new ReflectionTargetService();

        // 2. 클래스 객체 얻기
        Class<?> clazz = target.getClass();

        // 🔸 private 필드 접근 및 값 설정
        Field privateField = clazz.getDeclaredField("age");
        privateField.setAccessible(true); // 접근 허용
        privateField.set(target, 30); // 값 설정

        System.out.println("private 필드 age의 값: " + privateField.get(target));
        // private 필드 age의 값: 30

        // 🔸 private 메서드 호출
        Method privateMethod = clazz.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true); // 접근 허용
        privateMethod.invoke(target); // 메서드 실행
        // Private 메소드를 실행했습니다.
    }

    @Test
    void getConstructor() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 클래스 객체 가져오기
        Class<?> clazz = Class.forName("study.reflection.ReflectionTargetService");

        // 기본 생성자 가져오기
        Constructor<?> constructor = clazz.getConstructor(); // public 생성자만 가져옴
        Object instance = constructor.newInstance(); // 객체 생성

        System.out.println("생성된 객체: " + instance.getClass().getName());
        // 생성된 객체: study.reflection.ReflectionTargetService
    }

    @Test
    void getClassName() throws ClassNotFoundException {
        // 1. Class 객체 가져오기 (세 가지 방법)
        // 방법 1: 클래스 리터럴 사용 (가장 일반적이고 권장되는 방법)
        Class<ReflectionTargetService> clazz1 = ReflectionTargetService.class;
        System.out.println("방법 1 - Class 이름: " + clazz1.getName());

        // 방법 2: 인스턴스의 getClass() 메소드 사용
        ReflectionTargetService instance = new ReflectionTargetService();
        Class<? extends ReflectionTargetService> clazz2 = instance.getClass();
        System.out.println("방법 2 - Class 이름: " + clazz2.getName());

        // 방법 3: Class.forName() 메소드 사용 (클래스 이름을 문자열로 지정)
        // 이 방법은 클래스 로더가 클래스를 로드하고 초기화할 때 사용됩니다.
        Class<?> clazz3 = Class.forName("study.reflection.ReflectionTargetService");
        System.out.println("방법 3 - Class 이름: " + clazz3.getName());
    }

    @Test
    void getClassPackage() {
        Class<ReflectionTargetService> clazz1 = ReflectionTargetService.class;
        // 패키지 정보 가져오기
        System.out.println("패키지 이름: " + clazz1.getPackage().getName());

        Class<?>[] interfaces = clazz1.getInterfaces();
        if (interfaces.length > 0) {
            System.out.println("구현 인터페이스:");
            for (Class<?> intf : interfaces) {
                System.out.println("  - " + intf.getName());
            }
        } else {
            System.out.println("구현하고 있는 인터페이스 없음");
        }
    }

    @Test
    void getClassMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 1. ReflectionTargetService 클래스의 Class 객체 가져오기
        Class<?> clazz = ReflectionTargetService.class;

        // 2. ReflectionTargetService 인스턴스 생성 (메소드 호출을 위해 필요)
        ReflectionTargetService instance = new ReflectionTargetService();

        // 3. privateMethod() 메소드 가져오기
        // getMethod()는 public 메소드만 가져올 수 있습니다.
        // getDeclaredMethod()는 선언된 모든 메소드(public, private, protected 포함)를 가져올 수 있습니다.
        Method privateMethod = clazz.getDeclaredMethod("privateMethod");
        System.out.println("가져온 private 메소드 이름: " + privateMethod.getName());

        // 4. private 메소드 접근 허용 설정
        // private 메소드에 접근하려면 setAccessible(true)를 호출해야 합니다.
        // 이 설정이 없으면 IllegalAccessException이 발생합니다.
        privateMethod.setAccessible(true);
        System.out.println("private 메소드 접근 가능 설정 완료.");

        // 5. private 메소드 실행
        // invoke(Object obj, Object... args) 메소드를 사용하여 메소드를 실행합니다.
        // 첫 번째 인자는 메소드를 실행할 객체 인스턴스이고, 두 번째 인자부터는 메소드의 매개변수입니다.
        privateMethod.invoke(instance);
        System.out.println("private 메소드 실행 완료.");

        System.out.println("\n--- Reflection을 이용한 Protected 메소드 호출 ---");
        // protectedMethod() 메소드 가져오기
        Method protectedMethod = clazz.getDeclaredMethod("protectedMethod");
        System.out.println("가져온 protected 메소드 이름: " + protectedMethod.getName());

        // protected 메소드도 private과 동일하게 setAccessible(true) 필요 (다른 패키지에서 접근 시)
        // Reflection을 통해 접근할 때는 가시성 규칙을 무시하므로, 일반적으로 setAccessible(true)가 필요합니다.
        protectedMethod.setAccessible(true);
        System.out.println("protected 메소드 접근 가능 설정 완료.");

        // protected 메소드 실행
        protectedMethod.invoke(instance);
        System.out.println("protected 메소드 실행 완료.");
    }
}