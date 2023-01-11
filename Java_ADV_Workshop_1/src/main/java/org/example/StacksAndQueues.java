package org.example;

import java.util.ArrayDeque;

public class StacksAndQueues {
    public static void main(String[] args) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Top of the stack: " + stack.peek());

        while (!stack.isEmpty()) {
            int current = stack.pop();
            System.out.println(current);
        }

        ArrayDeque<String> queue = new ArrayDeque<>();
        queue.offer("Pesho");
        queue.offer("Gosho");
        queue.offer("Sasho");

        System.out.println("Front of the queue: " + queue.peekFirst());

        while (!queue.isEmpty()) {
            String name = queue.poll();
            System.out.println(name);
        }
    }
}
