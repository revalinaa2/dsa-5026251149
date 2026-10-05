package lw03.unguided;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String [] args) throws FileNotFoundException {
        Map<String, Integer> enrollment = new LinkedHashMap<>();
        List<String> courses = new ArrayList<>();
        List<String> check = new ArrayList<>();

        int rejected = 0;
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String operation = parts[0];
            String course = parts[1];

            if (operation.equals("REGISTER")) {
                int count = Integer.parseInt(parts[2]);
                if (count <= 0) {
                    rejected++;
                } else if (enrollment.containsKey(course)) {
                    int current = enrollment.get(course);
                    enrollment.put(course, current + count);
                } else {
                    enrollment.put(course, count);
                    courses.add(course);
                }
                
            } else if (operation.equals("WITHDRAW")) {
                int count = Integer.parseInt(parts[2]);
                if (count <= 0) {
                    rejected++;
                } else if (enrollment.containsKey(course) && enrollment.get(course) >= count) {
                    int current = enrollment.get(course);
                    enrollment.put(course, current - count);
                } else {
                    rejected++;
                }

            } else if (operation.equals("CHECK")) {
                if (enrollment.containsKey(course)) {
                    check.add(course + ": "+ enrollment.get(course) + " students");
                } else {
                    check.add(course + ": Not found");
                }
            }
        }

        scanner.close();

        System.out.println("===== Enrollment Checks =====");

        for (String result : check) {
            System.out.println(result);
        }

        System.out.println();
        System.out.println("===== Final Enrollment =====");

        for (String course : courses) {
            System.out.println(course + ": "+ enrollment.get(course) + " students");
        }

        System.out.println();
        System.out.println("Rejected operations: " + rejected);

    }
}

