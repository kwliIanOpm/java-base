package test.lang.invoke;

import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

public class MethodTypeTest {
    public static void main(String[] args) {
        MethodType methodType = MethodType.methodType(String.class, int.class, int.class);
        System.out.println(methodType.toMethodDescriptorString());
    }
}
