package ru.dima.study.lesson4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        new Worker().main();
    }

}


class Worker{
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();

    Object lock1 = new Object();     // объект монитор
    Object lock2 = new Object();     // объект монитор

    Random random = new Random();

    // один монитор в одно время может выполнять один метод

    public void addToList1() throws InterruptedException {
        synchronized (lock1) {
            Thread.sleep(1);
            list1.add(random.nextInt(100));
        }
    }

    public synchronized void addToList2() throws InterruptedException {
        synchronized (list2) {
            Thread.sleep(1);
            list2.add(random.nextInt(100));
        }
    }

    public void work() throws InterruptedException {
        for(int a = 0; a < 1000;a++){
            addToList1();
            addToList2();
        }
    }
    public void main() throws InterruptedException {
        long befor = System.currentTimeMillis();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    work();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    work();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        long after = System.currentTimeMillis();

        System.out.println((after - befor) + " Время выпонения");

        System.out.println("List1 : " + list1.size());
        System.out.println("List2 : " + list2.size());
    }
}