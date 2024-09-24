import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int maxAttempts = 5; // Limit the number of attempts per round
        int score = 0; // Track user's score based on rounds won
        boolean playAgain;

        System.out.println("Welcome to the Number Guessing Game!");
        
        do {
            int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
            int attempts = 0;
            boolean hasWon = false;
            
            System.out.println("\nI'm thinking of a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess the number.");
            
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You've guessed the correct number in " + attempts + " attempts.");
                    hasWon = true;
                    score++;
                    break;
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Too low!");
                }
            }

            if (!hasWon) {
                System.out.println("Sorry, you've used all attempts. The correct number was " + numberToGuess);
            }
            
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");

        } while (playAgain);
        
        System.out.println("Game over! Your total score: " + score + " round(s) won.");
        scanner.close();
    }
}
