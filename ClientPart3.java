package Client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.CompletableFuture;

import hello.Hello;

public class ClientPart3 {
    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registry.lookup("Hello");

            // Synchronous
            System.out.println("Synchronous RPC : add(i, j) : " + stub.add(4, 8));
            Integer[] nums = { 2, 9, 5, 7, 10, 1 };
            Integer[] sort = stub.sort(nums);
            System.out.println("Synchronous RPC : sort(arrayA) : ");
            for (int i : sort) {
                System.out.println(i);
            }

            // Asynchronous
            CompletableFuture.supplyAsync(() -> {
                Integer resp = null;
                try {
                    resp = stub.add(4, 10);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                return resp;
            }).thenAccept(r -> {
                System.out.println("Asynchronous RPC : add(i, j) : " + r);
            });

            CompletableFuture.supplyAsync(() -> {
                Integer[] n1 = { 2, 9, 5, 7, 10, 1 };
                try {
                    stub.sort(n1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return n1;
            }).thenAccept(r -> {
                System.out.println("Asynchronous RPC : sort(arrayA) : ");
                for (int i : r) {
                    System.out.println(i);
                }
            }).get();

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}