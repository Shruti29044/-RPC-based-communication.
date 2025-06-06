package Client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import hello.Hello;

public class ClientPart2 {
    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registry.lookup("Hello");
            String root = "C:\\Users\\patel\\OneDrive\\UTA\\project1\\src\\Client\\";
            File file = new File(root);
            WatchService watchService = FileSystems.getDefault().newWatchService();
            WatchKey key = file
                    .toPath()
                    .register(
                            watchService,
                            StandardWatchEventKinds.ENTRY_CREATE,
                            StandardWatchEventKinds.ENTRY_DELETE,
                            StandardWatchEventKinds.ENTRY_MODIFY);
            while (true) {
                for (WatchEvent event : key.pollEvents()) {
                    Path p = (Path) event.context();
                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                        BufferedInputStream in = new BufferedInputStream(
                                new FileInputStream(root + p.getFileName().toString()));
                        byte[] uploadData = new byte[p.getFileName().toString().length()];
                        in.read(uploadData, 0, uploadData.length);
                        stub.createFile(p.getFileName().toString(), uploadData);
                        in.close();
                        System.out.println("File created on the client side.");
                    } else if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                        if (stub.deleteFile(p.getFileName().toString())) {
                            System.out.println("File has been deleted.");
                        } else {
                            System.out.println("Operation Failed.");
                        }
                    } else if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                        BufferedInputStream in = new BufferedInputStream(
                                new FileInputStream(root + p.getFileName().toString()));
                        byte[] modifyData = new byte[p.getFileName().toString().length()];
                        in.read(modifyData, 0, modifyData.length);
                        in.close();
                        stub.modifyFile(p.getFileName().toString(), modifyData);
                        System.out.println("File is modified on the server.");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}