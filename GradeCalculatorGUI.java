package codsoft;

import java.util.Scanner;

public class GradeCalculatorGUI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Number of subjects
        System.out.print("Enter the number of subjects: ");
        int numberOfSubjects = scanner.nextInt();

        // Array to hold marks
        int[] marks = new int[numberOfSubjects];

        // Input marks
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextInt();
            // Validate input
            while (marks[i] < 0 || marks[i] > 100) {
                System.out.print("Invalid input! Please enter marks between 0 and 100: ");
                marks[i] = scanner.nextInt();
            }
        }

        // Calculate total marks
        int totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }

        // Calculate average percentage
        double averagePercentage = (double) totalMarks / numberOfSubjects;

        // Grade calculation
        String grade = calculateGrade(averagePercentage);

        // Display results
        System.out.println("Total Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%%n", averagePercentage);
        System.out.println("Grade: " + grade);

        // Close scanner
        scanner.close();
    }

    private static String calculateGrade(double average) {
        if (average >= 90) return "A+";
        else if (average >= 80) return "A";
        else if (average >= 70) return "B";
        else if (average >= 60) return "C";
        else if (average >= 50) return "D";
        else return "F";
    }
}