package test.lang;

import org.apache.lucene.util.RamUsageEstimator;

public class ObjectTest {

    private int a = 0;
    private int c = 0;

    public static void main(String[] args) {
        Object o = new Object();
        long l = RamUsageEstimator.sizeOf(o);
        System.out.println(l);

        System.out.println(RamUsageEstimator.sizeOf(new ObjectTest()));
    }
}
