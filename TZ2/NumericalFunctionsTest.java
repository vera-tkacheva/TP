package com.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class NumericalFunctionsTest {
private ArrayList<Integer> list;

    NumericalFunctionsTest() throws IOException {
        list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader("input0.txt"));
        String[] numsArray = reader.readLine().split(" ");
        for (int i = 0; i < numsArray.length; i++) {
            list.add(Integer.parseInt(numsArray[i]));
        }
    }

    @Test
    void min() {
        int min = Integer.MAX_VALUE;
        for (int num : list) {
            if (num < min) {
                min = num;
            }
        }

        assertEquals(8, min);
    }

    @Test
    void max() {
        int max = Integer.MIN_VALUE;
        for (int num : list) {
            if (num > max) {
                max = num;
            }
        }

        assertEquals(999, max);
    }

    @Test
    void sum() {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }

        assertEquals(49533, sum);
    }

    @Test
    void mult() {
        int mult = 1;
        for (int num : list) {
            mult *= num;
        }

        assertEquals(0, mult);
    }

    @Test
    void middle() {
        long sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        float middleValue = (float) sum / list.size();

        assertEquals(495.3299865722656, middleValue, 0.001);
    }

    @Test
    void minTime() {
        long t1 = System.nanoTime();
        this.min();
        long t2 = System.nanoTime();

        System.out.println("Время работы min: " + (t2 - t1));
    }

    @Test
    void maxTime() {
        long t1 = System.nanoTime();
        this.max();
        long t2 = System.nanoTime();

        System.out.println("Время работы max: " + (t2 - t1));
    }

    @Test
    void sumTime() {
        long t1 = System.nanoTime();
        this.max();
        long t2 = System.nanoTime();

        System.out.println("Время работы sum: " + (t2 - t1));
    }

    @Test
    void multTime() {
        long t1 = System.nanoTime();
        this.mult();
        long t2 = System.nanoTime();

        System.out.println("Время работы mult: " + (t2 - t1));
    }

    @Test
    void divisionByZero() {

        int min = Integer.MAX_VALUE;
        for (int num : list) {
            if (num < min) {
                min = num;
            }
        }

        int max = Integer.MIN_VALUE;
        for (int num : list) {
            if (num > max) {
                max = num;
            }
        }
        final float dividend = (float) max;
        final float divisor = (float) min;

        if (divisor == 0) {
            assertThrows(ArithmeticException.class, () -> {
                float division = dividend / divisor;
            }, "Деление на 0!");
        }
    }



@Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    @Test
    @Disabled
    void timeCheck() throws InterruptedException {
        Thread.sleep(200);

        long t1 = System.nanoTime();
        this.middle();
        long t2 = System.nanoTime();

        System.out.println("Время вычисления среднего арифметического: " + (t2 - t1));
    }
}
