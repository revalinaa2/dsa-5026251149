package lw04.unguided;

import java.util.Scanner;

public class Main {
    static void sorting(int[] arr, int pass) {
        int n = arr.length;

        for(int i = 0; i < pass; i++) {
            int posMin = i;

            for(int j = i + 1; j < n; j++) {
                if (arr[j] < arr[posMin]) {
                    posMin = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[posMin];
            arr[posMin] = temp;
        }
    }
    
    static int search(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String [] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("cards2.txt"));

        int n = sc.nextInt();
        int[] cards = new int[n];

        for (int i = 0; i < n; i++) {
            cards[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] requested = new int[m];

        for (int i = 0; i < m; i++) {
            requested[i] = sc.nextInt();
        }

        sc.close();

        sorting(cards, 2);

        for (int i = 0; i < cards.length; i++) {
            System.out.print(cards[i]);

            if (i < cards.length - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();

        for (int i = 0; i < requested.length; i++) {
            int index = search(cards, requested[i]);

            if (index != -1) {
                System.out.println(requested[i] + ": FOUND at index " + index);
            } else {
                System.out.println(requested[i] + ": NOT FOUND");
            }
        }
    }
}
