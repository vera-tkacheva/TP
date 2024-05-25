package com.test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NumericalFunctions {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input0.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<Integer> numsArray = new ArrayList<>();
        String[] stringNumsArray = scanner.nextLine().split(" ");
        for (int i = 0; i < stringNumsArray.length; i++) {
            numsArray.add(Integer.parseInt(stringNumsArray[i]));
        }
        int min = min(numsArray);
        int max = max(numsArray);
        long sum = sum(numsArray);
        long mult = mult(numsArray);
        float middle = middle(numsArray);

        System.out.println("Минимум: " + min);
        System.out.println("Максимум: " + max);
        System.out.println("Сумма: " + sum);
        System.out.println("Произведение: " + mult);
        System.out.println("Среднее арифметическое: " + middle);

    }

    public static int min(ArrayList<Integer> numsArray) {
        int min = Integer.MAX_VALUE;
        for (int num : numsArray) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    public static int max(ArrayList<Integer> numsArray) {
        int max = Integer.MIN_VALUE;
        for (int num : numsArray) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static long sum(ArrayList<Integer> numsArray) {
        int sum = 0;
        for (int num : numsArray) {
            sum += num;
        }
        return sum;
    }

    public static long mult(ArrayList<Integer> numsArray) {
        int mult = 1;
        for (int num : numsArray) {
            mult *= num;
        }
        return mult;
    }

    public static float middle(ArrayList<Integer> numsArray) {
        long sum = 0;
        for (int i = 0; i < numsArray.size(); i++) {
            sum += numsArray.get(i);
        }
        float middleValue = (float) sum / numsArray.size();
        return middleValue;
    }
}
//assert exception для деления на ноль
//actions in github - workflow