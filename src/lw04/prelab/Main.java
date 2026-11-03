package lw04.prelab;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void sorting(int[] branches) {
        for (int i = 1; i <= 3; i++) {
            int key = branches[i];
            int j = i - 1;

            while (j >= 0 && branches[j] < key) {
                branches[j + 1] = branches[j];
                j--;
            }

            branches[j + 1] = key;
        }
    }

    public static int search(int[] branches, int target) {
        for (int i = 0; i < branches.length; i++) {
            if (branches[i] == target) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(new File("branches.txt"));

            int n = input.nextInt();
            int[] branches = new int[n];

            for (int i = 0; i < n; i++) {
                branches[i] = input.nextInt();
            }

            int m = input.nextInt();
            int[] targets = new int[m];

            for (int i = 0; i < m; i++) {
                targets[i] = input.nextInt();
            }

            input.close();

            sorting(branches);

            for (int i = 0; i < branches.length; i++) {
                System.out.print(branches[i]);

                if (i < branches.length - 1) {
                    System.out.print(" ");
                }
            }

            System.out.println();

            for (int i = 0; i < targets.length; i++) {
                int index = search(branches, targets[i]);

                if (index != -1) {
                    System.out.println(
                        targets[i] + ": FOUND at index " + index
                    );
                } else {
                    System.out.println(
                        targets[i] + ": NOT FOUND"
                    );
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File branches.txt not found.");
        }
    }
}
