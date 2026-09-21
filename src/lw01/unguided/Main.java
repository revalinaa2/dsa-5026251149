package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("rentals.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("rentals.txt tidak ditemukan di direktori kerja.");
            return;
        }

        int total = scanner.nextInt();
        Rental[] rentals = new Rental[total];

        for (int i = 0; i < total; i++) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();

            if (type.equals("LAPTOP")) {
                rentals[i] = new LaptopRental(id, days, units);
            } else {
                rentals[i] = new ProjectorRental(id, days, units);
            }
        }

        scanner.close();

        for (Rental rental : rentals) {
            System.out.println(rental.summary());
        }
    }
}