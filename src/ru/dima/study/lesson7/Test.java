package ru.dima.study.lesson7;


import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        WaitAndNotify wn = new WaitAndNotify();
     Thread thread1 = new Thread(new Runnable() {
         @Override
         public void run() {
             try {
                 wn.produce();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     });

     Thread thread2 = new Thread(new Runnable() {
         @Override
         public void run() {
             try {
                 wn.consumer();
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

class WaitAndNotify{

    public void produce() throws InterruptedException {
       synchronized (this){ // обязательно условие synchronized блок
           System.out.println("Start execute in produce");
           wait();                    // поток доходит к методу wait(); и отдает монитор другому потоку
           System.out.println("End execute in produce");

       }
    }
    
    public void consumer() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        synchronized (this){ // обязательное условие synchronized блок
            Thread.sleep(2000);
            System.out.println("Hello epta");

            scanner.nextLine();

            notify();        // когда поток выполняет этот метод монитор передается wait();
                             // все методы после notify() выполнется а зетем он отдаст монитор
        }
    }
    
    
    
}
