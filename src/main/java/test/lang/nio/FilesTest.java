package test.lang.nio;

import java.nio.channels.spi.SelectorProvider;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class FilesTest {
    public static void main(String[] args) throws Exception {
        SelectorProvider provider = SelectorProvider.provider();
        System.out.println(provider);
//        readAttributes();
    }

    public static void readAttributes() throws Exception {
        Path filePath = Paths.get("/home/kwli/IdeaProjects/java-base/src/main/java/test/lang/nio/FilesTest.java");
        BasicFileAttributes ra = Files.readAttributes(filePath, BasicFileAttributes.class);
        System.out.println("CREATION TIME:" + ra.creationTime());
        System.out.println("LAST ACCESS TIME:" + ra.lastAccessTime());
        System.out.println("FILE SIZE:" + ra.size());
        System.out.println("LAST MODIFIED:" + ra.lastModifiedTime());
        System.out.println("IS SYSBOLIC LINK:" + ra.isSymbolicLink());
        System.out.println("IS FOLDER:" + ra.isDirectory());
        System.out.println("IS FILE:" + ra.isRegularFile());
    }
}
