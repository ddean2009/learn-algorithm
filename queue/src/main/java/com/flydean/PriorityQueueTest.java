package com.flydean;

import java.util.PriorityQueue;

public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue queMin = new PriorityQueue<Integer>((a, b) -> (b - a));
        PriorityQueue queMax = new PriorityQueue<Integer>((a, b) -> (a - b));

        queMin.offer(1);
        queMin.offer(2);
        System.out.println(queMin.peek());
        System.out.println(queMin.poll());

        queMax.offer(1);
        queMax.offer(2);
        System.out.println(queMax.peek());
        System.out.println(queMax.poll());




    }
}
