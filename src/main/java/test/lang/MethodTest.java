package test.lang;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MethodTest {
    public static void main(String[] args) throws Exception {
        Method method = MethodTest.class.getDeclaredMethod("test", Map.class);
        System.out.println(Arrays.toString(method.getGenericParameterTypes()));
    }

    public List<String> test(Map<String, Date> map){
        return null;
    }
}
