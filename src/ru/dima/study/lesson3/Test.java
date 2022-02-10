package ru.dima.study.lesson3;

public class Test {
    private int counter;
    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        test.doWork();

    }
    public synchronized void increment(){  // методы могут быть синхронизированы и доступ к ним в одно время имеет один поток
        counter++;
    }
    public void doWork() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int a = 0; a < 1000; a++)
                     increment();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int a = 0; a < 1000; a++)
                increment();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join(); // дальше этого метода поток main не пойдет

        System.out.println(counter); // только после завершения

        System.out.println("Hello from doWork"); // только после завершения

    }
}
