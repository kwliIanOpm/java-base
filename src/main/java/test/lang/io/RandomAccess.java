package test.lang.io;


import java.io.RandomAccessFile;

public class RandomAccess {
    public static void main(String[] args)  throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("src/main/resources/hello.txt","rw");
        randomAccessFile.seek(3);
        char read = (char) randomAccessFile.read();
        randomAccessFile.write('#');
        System.out.println(read);
        randomAccessFile.close();
    }
}
