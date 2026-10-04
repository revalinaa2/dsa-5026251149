package lw03.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        problem1();
        problem2();
        problem3();
    }

    // Problem 1

    static void problem1() throws FileNotFoundException {
        List<String> playlist = new ArrayList<>();
        Scanner scanner = new Scanner(new File("playlist.txt"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ", 2);
            String operation = parts[0];

            if (operation.equals("ADD")) {
                String song = parts[1];
                playlist.add(song);
            } else if (operation.equals("INSERT")) {
                String[] insertParts = line.split(" ", 3);
                int index = Integer.parseInt(insertParts[1]);
                String song = insertParts[2];
                playlist.add(index, song);
            } else if (operation.equals("REMOVE")) {
                String song = parts[1];
                playlist.remove(song);
            }
        }

        scanner.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());

        for(int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    // Problem 2

    static void problem2() throws FileNotFoundException {
        Set<String> participants = new LinkedHashSet<>();
        int duplicateRegistrations = 0;
        Scanner scanner = new Scanner(new File("participants.txt"));

        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            if (participants.contains(name)) {
                duplicateRegistrations++;
            } else {
                participants.add(name);
            }
        }

        scanner.close();
        
        System.out.println();
        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());

        int number = 1;

        for (String name : participants) {
            System.out.println(number + ". " + name);
            number++;
        }

        System.out.println("Duplicate registrations: " + duplicateRegistrations);
    }

    // Problem 3

    static void problem3() throws FileNotFoundException {
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;
        Scanner scanner = new Scanner(new File("inventory.txt"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                if (inventory.containsKey(product)) {
                    int currentStock = inventory.get(product);
                    inventory.put(product, currentStock + quantity);
                } else {
                    inventory.put(product, quantity);
                }
            } else if (type.equals("SELL")) {
                if (inventory.containsKey(product) && inventory.get(product) >= quantity) {
                    int currentStock = inventory.get(product);
                    inventory.put(product, currentStock - quantity);
                } else {
                    failedSales++;
                } 
            }
        }

        scanner.close();

        System.out.println();
        System.out.println("===== Problem 3 =====");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("Failed sales: " + failedSales);

    }
}
