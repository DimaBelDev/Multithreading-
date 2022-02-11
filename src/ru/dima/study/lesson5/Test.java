package ru.dima.study.lesson5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for(int a = 0; a < 5;a++)
            executorService.submit(new Work(a));

        executorService.shutdown(); // все задания переданы, начинаем
        
        executorService.awaitTermination(1, TimeUnit.DAYS); // время выдиляемое на выполнение

    }

}

class Work implements Runnable{
    private int id;

    public Work(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Work " + id + "was completed");
    }
}