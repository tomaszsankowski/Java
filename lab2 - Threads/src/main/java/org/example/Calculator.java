package org.example;
import java.util.*;
public class Calculator implements Runnable {

    Queue<Integer> queue;
    Map<Integer, Boolean> results;
    Calculator() {
        results = new TreeMap<>();
        queue = new LinkedList<>();
        //queue.add(12345678);
        //queue.add(87654321);
        //queue.add(12345666);
        //queue.add(32435435);
        //queue.add(43654765);
        //queue.add(455645654);
        //queue.add(435436546);
        //queue.add(765435321);
        //queue.add(1234544666);
    }

    private final Object queueVar = new Object();
    private final Object resultsVar = new Object();

    public void printNumbers()
    {
        System.out.println("Numbers that were checked :");
        for (Map.Entry<Integer, Boolean> entry : results.entrySet()) {
            System.out.println("Number = " + entry.getKey() + ", isPrime = " + entry.getValue());
        }
        System.out.println("Numbers that were left in queue :");
        for(Integer nb : queue)
        {
            System.out.println(nb);
        }
    }
    public void addTask(int task) {
        synchronized (queueVar) {
            queue.add(task);
            queueVar.notify();
        }
    }
    public int takeTask() throws InterruptedException {
        synchronized (queueVar) {
            while (queue.isEmpty()) {
                queueVar.wait();
            }
            int out = queue.remove();
            queueVar.notify();
            return out;
        }
    }
    public void giveResult(int n, boolean isPrime) {
        synchronized (resultsVar) {
            results.put(n, isPrime);
            resultsVar.notify();
        }
    }
    @Override
    public void run() {
        int n = 0;
        boolean checked = true;
        try {
            while (!Thread.interrupted()) {
                n = takeTask();
                checked = false;
                boolean isPrime = true;
                Thread.sleep(5000);
                for (int i = n - 1; i > 1; i--) {
                    if (n % i == 0) {
                        isPrime = false;
                    }
                }
                System.out.print("By: " + Thread.currentThread().getName() + " thread. Number " + n);
                if (isPrime) {
                    System.out.print(" is prime!\n");
                } else {
                    System.out.print(" is not prime!\n");
                }
                giveResult(n, isPrime);
                checked = true;
            }
        } catch (InterruptedException e) {
            if(n!=0 && !checked)
            {
                addTask(n);
            }
            Thread.currentThread().interrupt();
        }
    }
}
