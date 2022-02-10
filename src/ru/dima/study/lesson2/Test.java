package ru.dima.study.lesson2;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        MyTread myTread = new MyTread();
        myTread.start();

        Scanner scanner = new Scanner(System.in);

        scanner.nextLine();

        myTread.shutDown();

    }
}

class MyTread extends Thread{
    private volatile boolean check = true; // volatile если переменная принадлежит одному потоку
                                           // ,а изменяем мы ее в другом ,то нужно помечать этим словом.
    @Override
    public void run() {
        while (check){
            System.out.println("Hello ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutDown(){
            this.check = false;

    }

}

