package org.example;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Calculator cal = new Calculator();

        int numOfThreads = Integer.parseInt(args[0]);

        Thread[] threads = new Thread[numOfThreads];
        for(int i=0;i<numOfThreads;i++)
        {
            threads[i] = new Thread(cal);
            threads[i].start();
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Insert number to check if its prime or 'exit' to leave : ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                int numberToCheck = Integer.parseInt(input);
                cal.addTask(numberToCheck);
            } catch (NumberFormatException e) {
                System.out.println("Wrong number format! Try again.");
            }
        }
        for(Thread thread : threads)
        {
            thread.interrupt();
        }

        System.out.println("All threads are ended!");
        cal.printNumbers();
    }
}