package test.lang.nio;


import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * 递归监听文件变化
 */
public class FileSystemTest {
    public static void main(String[] args)  throws Exception{
        FileSystem fileSystem = FileSystems.getDefault();
        WatchService watchService = fileSystem.newWatchService();
        Path path = Paths.get("/home/kwli/IdeaProjects/java-base/src/main/java/test/lang");
        WatchKey register = path.register(watchService, ENTRY_CREATE, OVERFLOW, ENTRY_DELETE, ENTRY_MODIFY);
        while (true){
            WatchKey take = watchService.take();
            List<WatchEvent<?>> watchEvents = take.pollEvents();
            watchEvents.forEach((w)->{
                Path context = (Path)w.context();
                Path resolve = ((Path) take.watchable()).resolve(context);
                boolean isDirectory = Files.isDirectory(resolve);
                if(w.kind()== ENTRY_CREATE&&isDirectory){
                    try {
                        resolve.register(watchService, ENTRY_CREATE, OVERFLOW, ENTRY_DELETE, ENTRY_MODIFY);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(context.getFileName()+"    "+w.kind());
            });
            take.reset();
        }
    }
}
