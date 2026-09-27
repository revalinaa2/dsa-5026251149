package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        try {
            Scanner scanner = new Scanner(new File("transactions.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] transaction = line.split(" ");
                transactions.add(transaction);

                String customerName = transaction[0];
                boolean customerExists = false;

                for (String[] customer : customers) {
                    if (customer[0].equals(customerName)) {
                        customerExists = true;
                        break;
                    }
                }

                if (!customerExists) {
                    customers.add(new String[]{customerName, "0"});
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("transactions.txt file not found.");
            return;
        }

        Queue<String[]> transactionQueue = new LinkedList<>();

        while (!transactions.isEmpty()) {
            transactionQueue.offer(transactions.removeFirst());
        }

        Stack<String[]> failedTransactions = new Stack<>();

        while (!transactionQueue.isEmpty()) {
            String[] transaction = transactionQueue.poll();
            String customerName = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            for (String[] customer : customers) {
                if (customer[0].equals(customerName)) {
                    int balance = Integer.parseInt(customer[1]);

                    if (type.equals("DEPOSIT")) {
                        balance += amount;
                        customer[1] = String.valueOf(balance);
                    } else if (type.equals("WITHDRAW")) {
                        if (amount > balance) {
                            failedTransactions.push(transaction);
                        } else {
                            balance -= amount;
                            customer[1] = String.valueOf(balance);
                        }
                    }
                    break;
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] customer : customers) {
            System.out.println(customer[0] + ": " + customer[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] transaction = failedTransactions.pop();
            System.out.println(transaction[0] + " " + transaction[1] + " " + transaction[2]);
        };
    }
}
