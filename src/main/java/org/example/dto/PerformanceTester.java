package org.example.dto;

public class PerformanceTester {
    public static void testMethodPerformance(Runnable method, String methodName) {
        long startTime = System.nanoTime();
        method.run();
        long endTime = System.nanoTime();

        System.out.println(methodName + " выполнен за " + (endTime - startTime) / 1_000_000 + " мс");
    }
}
