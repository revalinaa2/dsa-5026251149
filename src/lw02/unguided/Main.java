package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    static final int MAX_BORROW = 2;
    public static void main(String[] args) {
        LinkedList<String[]> requests = new LinkedList<>();
        LinkedList<String[]> books = new LinkedList<>();
        LinkedList<String[]> members = new LinkedList<>();
        LinkedList<String[]> success = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        books.add(new String[]{"Kalkulus", "2"});
        books.add(new String[]{"Fisika", "1"});
        books.add(new String[]{"Statistika", "2"});

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));

        while(scanner.hasNext()) {
            String[] request = new String[2];
            request[0] = scanner.next();
            request[1] = scanner.next();
            requests.add(request);
        }
        scanner.close();

        queue.addAll(requests);

        while(!queue.isEmpty()) {
            String[] request = queue.poll();
            String name = request[0];
            String title = request[1];

            String[] book = null;

            for (String[] data : books) {
                if (data[0].equals(title)) {
                    book = data;
                    break;
                }
            }

            String[] member = null;

            for (String[] data : members) {
                if (data[0].equals(name)) {
                    member = data;
                    break;
                }
            }

            if(member == null) {
                member = new String[]{name, "0"};
                members.add(member);
            }

            int stock = Integer.parseInt(book[1]);
            int borrowed = Integer.parseInt(member[1]);

            if(stock > 0 && borrowed < MAX_BORROW) {
                stock -= 1;
                borrowed += 1;
                book[1] = String.valueOf(stock);
                member[1] = String.valueOf(borrowed);
                success.add(request);
            } else {
                failed.push(request);
            }
        }
        
        System.out.println("=== Successfully Processed Request ===");
        
        for(String[] request : success) {
                System.out.println(request[0] + "  " + request[1]);
        }

        System.out.println("\n=== Remaining Book Stock ===");

        for(String[] book : books) {
            System.out.println(book[0] + "  " + book[1]);
        }

        System.out.println("\n=== Failed Request ===");

        while(!failed.isEmpty()) {
            String[] request = failed.pop();
            System.out.println(request[0] + "  " + request[1]);
        }
    }
}
