package test.lang.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;

public class MethodHandleTest {
    public static void main(String[] args)  throws  Throwable{
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        MethodHandle getBytes = lookup.findVirtual(String.class, "getBytes", MethodType.methodType(byte[].class, String.class));
        MethodHandle methodHandle = getBytes.bindTo("hello world");
        byte[] o = (byte[])methodHandle.invokeExact("UTF-8");
        System.out.println(Arrays.toString(o));

        MethodHandle valueOf = lookup.findStatic(Integer.class, "valueOf", MethodType.methodType(Integer.class, int.class));
        Integer invoke = (Integer)valueOf.invoke(128);
        System.out.println(invoke);


    }
}
