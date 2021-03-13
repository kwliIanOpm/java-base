package test.lang;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class FieldTest {

    public static void main(String[] args)  throws Exception{
        Field a = A.class.getDeclaredField("a");
        System.out.println(a.getDeclaringClass());

        Field list = A.class.getDeclaredField("list");
        System.out.println(list);
        System.out.println(list.getGenericType());
    }
    class A{
        private String a;
        private List<String> list;
    }
}
