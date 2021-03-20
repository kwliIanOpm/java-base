package test.lang.io;

import org.springframework.util.StreamUtils;

import java.io.BufferedInputStream;
import java.nio.charset.StandardCharsets;

public class BufferedStream {
    public static void main(String[] args) throws Exception{
        BufferedInputStream stream =
                (BufferedInputStream)ClassLoader.getSystemResourceAsStream("hello.txt");
        System.out.println(stream);
        stream.mark(-1);
        String s = StreamUtils.copyToString(stream, StandardCharsets.UTF_8);
        System.out.println(s);
        stream.reset();
        String s1 = StreamUtils.copyToString(stream, StandardCharsets.UTF_8);
        System.out.println(s1);
    }
}
