package test.lang;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @author kwli
 */
public class ClassTest {
    public static void main(String[] args)  throws Exception{
//          constructor();
        cLassToString();
    }


    /**
     * 无法手动new Class,即使是反射
     * @throws Exception
     */
    public static void  constructor() throws Exception{
        Constructor<?> constructor = Class.class.getDeclaredConstructors()[0];
        constructor.setAccessible(true); //Cannot make a java.lang.Class constructor accessible
        Object o = constructor.newInstance((Object) null);
    }

    public static void cLassToString(){
        System.out.println(Class.class.toString()); //class java.lang.Class
        System.out.println(List.class.toString()); //interface java.util.List
        System.out.println(int.class.toString()); // int
    }
}
