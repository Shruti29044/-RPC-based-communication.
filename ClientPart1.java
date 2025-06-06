package Client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import hello.Hello;

public class ClientPart1 extends Thread {

    public static String host;

    public static void fileList() {
        File[] pathList;
        File directory = new File("C:\\Users\\patel\\OneDrive\\UTA\\project1\\src\\Server\\part-1\\");
        FilenameFilter filefilter = new FilenameFilter() {
            @Override
            public boolean accept(File directory, String fileName) {
                return fileName.endsWith(".txt");
            }
        };
        pathList = directory.listFiles(filefilter);
        System.out.println("List of files available on the server:");
        for (File list : pathList) {
            System.out.println(list.getName());
        }
    }

    public void run() {

        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registry.lookup("Hello");
            String root = "C:\\Users\\patel\\OneDrive\\UTA\\project1\\src\\Client\\";
            Scanner scanner = new Scanner(System.in);
            String choice = "";
            while (!choice.equalsIgnoreCase("5")) {
                System.out.println("Select your choice:");
                System.out.println("1. Upload");
                System.out.println("2. Download");
                System.out.println("3. Rename");
                System.out.println("4. Delete");
                System.out.println("5. Exit");
                System.out.println();
                System.out.print("Enter your choice:");
                choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        System.out.print("Enter file name:");
                        String uploadFileName = scanner.nextLine();
                        String uploadPath = root + uploadFileName;
                        BufferedInputStream in = new BufferedInputStream(new FileInputStream(uploadPath));
                        byte[] uploadData = new byte[uploadFileName.length()];
                        in.read(uploadData, 0, uploadData.length);
                        stub.uploadFile(uploadData, uploadFileName);
                        in.close();
                        break;

                    case "2":
                        ClientPart1.fileList();
                        System.out.print("Choose any file:");
                        String downloadFileName = scanner.nextLine();
                        byte[] downloadData = stub.downloanFile(downloadFileName);
                        if (downloadData[0] != 1) {
                            String DownloadPath = root + "Download\\" + downloadFileName;
                            File obj = new File(DownloadPath);
                            if (obj.createNewFile()) {
                                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(obj));
                                out.write(downloadData, 0, downloadData.length);
                                out.flush();
                                out.close();
                                System.out.println("File Successfully Downloaded.");
                            } else {
                                System.out.println("File already Exist.");
                            }
                        } else {
                            System.out.println("File not available on the Server.");
                        }
                        break;

                    case "3":
                        ClientPart1.fileList();
                        System.out.print("Select file to rename:");
                        String oldName = scanner.nextLine();
                        System.out.print("Enter new Name:");
                        String newName = scanner.nextLine();
                        if (stub.renameFile(oldName, newName)) {
                            System.out.println("File Successfully Rename");
                        } else {
                            System.out.println("Operation Failed.");
                        }
                        break;

                    case "4":
                        ClientPart1.fileList();
                        System.out.print("Select file to delete:");
                        String delete = scanner.nextLine();
                        if (stub.deleteFile(delete)) {
                            System.out.println("Deleted the file: " + delete);
                        } else {
                            System.out.println("Failed to delete the file.");
                        }
                        break;

                    case "5":
                        break;
                }
            }
            scanner.close();

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ClientPart1 thread = new ClientPart1();
        thread.start();
        host = (args.length < 1) ? null : args[0];
    }
}