package test.lang.invoke;


import java.math.BigDecimal;

public class CallSiteTest {
    public static void main(String[] args)  {
        BigDecimal bigDecimal = new BigDecimal("1.0");
        new Thread(() -> {
            System.out.println(bigDecimal);
        }).start();
    }

}
