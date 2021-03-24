package test.lang.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

import static java.nio.file.FileVisitResult.CONTINUE;

public class FileTest {
    public static void main(String[] args) throws Exception {
        Path source = Paths.get("/home/kwli/IdeaProjects/java-base/src/main/java/test/lang/");
        Path target = Paths.get("/home/kwli/IdeaProjects/java-base/lkw/");
        Files.walkFileTree(source, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE,
                new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                            throws IOException
                    {
                        Path targetdir = target.resolve(source.relativize(dir));
                        try {
                            Files.copy(dir, targetdir);
                        } catch (FileAlreadyExistsException e) {
                            if (!Files.isDirectory(targetdir))
                                throw e;
                        }
                        return CONTINUE;
                    }
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                            throws IOException
                    {
                        Files.copy(file, target.resolve(source.relativize(file)));
                        return CONTINUE;
                    }
                });
    }
}
