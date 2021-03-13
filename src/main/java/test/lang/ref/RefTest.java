package test.lang.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

public class RefTest {
    public static void main(String[] args) throws Throwable {
        testWeak();
    }

    public static void testWeak() throws Throwable {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        ReferenceQueue<LocalDateTime> queue = new ReferenceQueue<>();
        PhantomReference<LocalDateTime> reference = new PhantomReference<>(now, queue);
        new Thread(() -> {
            while (true){
                try {
                    Field referent = Reference.class.getDeclaredField("referent");
                    referent.setAccessible(true);
                    Reference<? extends LocalDateTime> poll = queue.poll();
                    if(poll!=null){
                        System.out.println(referent.get(poll));
                    }
                }catch (Exception e){
                }
            }
        }).start();
        now = null;
        System.out.println("gc");
        Thread.sleep(100);
        System.gc();
        Thread.sleep(1000);
//        Reference<? extends LocalDateTime> poll = queue.poll();
//        Field referent = Reference.class.getDeclaredField("referent");
//        referent.setAccessible(true);
//        System.out.println(referent.get(poll));
    }

}
