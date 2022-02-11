package ru.dima.study.lesson8;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Test {
    public static void main(String[] args) throws InterruptedException {

        ProducerConsumer pc = new ProducerConsumer();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    pc.consumer();
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
}

class ProducerConsumer{
    private Queue<Integer> queue = new LinkedList<>();
    private final int LIMITED = 10;
      private final Object lock = new Object(); // объекты на которых мы синхронизируемся должны быть константа

    public void produce() throws InterruptedException {
        Random random = new Random();
        while (true) {                      // можем использовать if но для повторной проверки лучше while
            synchronized (lock) {           // обязательно синхронизироваться на одном и том же объекте
                while (queue.size() == LIMITED) {
                    lock.wait();
                 }

                queue.offer(random.nextInt(100));
                System.out.println("add");
                lock.notify();            // ВЫЗОВ notify(); бессмыслен пока не вызван wait(); где-нибудь
            }
        }
    }

    public void consumer() throws InterruptedException {
        while (true) {          // можем использовать if но для повторной проверки лучше while
            synchronized (lock) {
                while (queue.size() == 0) {
                    lock.wait();
                }

                System.out.println(queue.poll());
                lock.notify();
            }
            Thread.sleep(1000);
        }
    }

}