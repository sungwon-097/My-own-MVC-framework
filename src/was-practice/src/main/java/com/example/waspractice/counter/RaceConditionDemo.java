package com.example.waspractice.counter;

public class RaceConditionDemo
{
    public static void main(String[] args) {
        Counter counter = new Counter();
        // Singleton 객체를

        Thread t1 = new Thread(counter, "Thread 1");
        Thread t2 = new Thread(counter, "Thread 2");
        Thread t3 = new Thread(counter, "Thread 3");
        // 공유하여 상태를 유지하도록 멀티쓰레드 환경에서 사용한다면

        t1.start();
        t2.start();
        t3.start();
        // Race Condition 이 발생 할 수 있다
    }
}
