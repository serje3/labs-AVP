package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedList<Integer> list = new ConcurrentLinkedList<>();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            for (int i = 1; i <= 300; i++) {
                list.add(i);
                System.out.println("Added: " + i);
            }
        });

        executor.submit(() -> {
            for (int i = 1; i <= 100; i++) {
                boolean removed = list.remove(i);
                System.out.println("Removed: " + i + " - " + removed);
            }
        });

        executor.shutdown();
        boolean terminated = executor.awaitTermination(5, TimeUnit.SECONDS);

        if (terminated){
            System.out.println("Executor terminated");
        }

        System.out.println("Final List:");
        printList(list);
    }

    private static void printList(ConcurrentLinkedList<Integer> list) {
        System.out.println(list);
    }
}
