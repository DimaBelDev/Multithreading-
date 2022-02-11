package ru.dima.study.lesson6;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test {

    private  static BlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();



    }

    public static void produce() throws InterruptedException {
        Random random = new Random();

        while (true) {
            arrayBlockingQueue.put(random.nextInt(100)); // метод put потоко безопасный
        }
    }

    public static void  consumer() throws InterruptedException {
        while (true) {
            Thread.sleep(1);
            System.out.println(arrayBlockingQueue.take()); // take потоко безопасный
            System.out.println("Размер массива " + arrayBlockingQueue.size());
        }
    }


}
