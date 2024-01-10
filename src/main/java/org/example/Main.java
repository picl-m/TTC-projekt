package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static boolean isParsableToNumber(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void main(String[] args) {

        // Get file or user input
        ArrayList<Integer> inputNumbers = new ArrayList<>();

        if (args.length == 0) {
            System.out.println("Missing args");
            return;
        } else if (isParsableToNumber(args[0]) && Integer.parseInt(args[0]) > 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter numbers separated by spaces:");

            String[] inputArray = scanner.nextLine().split("\\s+");

            for (String input : inputArray) {
                if (isParsableToNumber(input)) {
                    inputNumbers.add(Integer.parseInt(input));
                }
            }
        } else {
            try {
                Scanner scanner = new Scanner(new File(args[0]));

                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    if (isParsableToNumber(data)) {
                        inputNumbers.add(Integer.parseInt(data));
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error occurred getting input file: " + e.getMessage());
                return;
            }
        }
        if (inputNumbers.isEmpty()) {
            System.out.println("The input is empty");
        }

        // Generate output
        ArrayList<Integer> outputNumbers = new ArrayList<>();

        if (inputNumbers.size() % 2 == 0) {
            for (int number : inputNumbers) {
                if (number % 2 == 0) {
                    outputNumbers.add(number);
                }
            }
        } else {
            for (int number : inputNumbers) {
                if (number % 2 != 0) {
                    outputNumbers.add(number);
                }
            }
        }

        // Output into file or to user
        if (args.length == 2) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]))) {
                writer.write(outputNumbers.toString());
                writer.close();
                System.out.println("Output was successfully written to: " + args[1]);
                return;
            } catch (IOException e) {
                System.out.println("Error occurred writing output file: " + e.getMessage());
            }
        }

        System.out.println("Result: " + outputNumbers);
    }
}
