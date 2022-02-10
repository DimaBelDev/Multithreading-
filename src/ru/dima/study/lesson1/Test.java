package ru.dima.study.lesson1;

public class Test {
    public static void main(String[] args) throws InterruptedException {
//        MyThread myThread = new MyThread();
//        myThread.start();
//
//        Thread thread2 = new Thread(new Runner());
//        thread2.start();

        MyThread myThread1 = new MyThread();
        myThread1.start();
        System.out.println("Hello from main thread");
//        System.out.println("i am going to sleep");
//        Thread.sleep(3000); // поток засыпает на 3 секунды
//        System.out.println("i am awake!");
    }
}

class Runner implements Runnable{

    @Override
    public void run() {
        for(int a = 0; a < 100; a++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("second thread " + a);
        }
    }
}

class MyThread extends Thread {

    @Override
    public void run() {
     for(int a = 0; a < 100; a++){
         try {
             Thread.sleep(1000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         System.out.println("first thread " + a);
     }
    }
}
