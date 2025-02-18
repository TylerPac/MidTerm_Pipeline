package com.mycompany.app;
/*
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
*/
/**
 * The Fibonacci class provides a method to compute the nth term of the Fibonacci sequence
 * using a recursive approach.
 * The Fibonacci sequence is defined as follows:
 * F(0) = 0, F(1) = 1, and F(n) = F(n-1) + F(n-2) for n > 1.
 */
public class App {

    /**
     * Computes the nth term in the Fibonacci sequence recursively.
     * 
     * @param n The index of the term to retrieve from the Fibonacci sequence. 
     *          Should be a non-negative integer.
     * @return The nth Fibonacci number. If n is 0, returns 0. If n is 1, returns 1. 
     *         For all other n, returns the sum of the previous two Fibonacci numbers.
     * @throws IllegalArgumentException if n is a negative number.
     */
    public static int fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n cannot be negative.");
        }
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    /**
     * The main method that demonstrates the use of the fibonacci method.
     * It calculates the 10th Fibonacci number and prints it to the console.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int n = 10;
        int nthFibonacci = fibonacci(n);

        System.out.println("The " + n + "th term of the Fibonacci sequence is " + nthFibonacci + ".");
    }
}
