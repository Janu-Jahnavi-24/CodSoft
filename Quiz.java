package day_02;

import java.util.Scanner;
import java.util.concurrent.*;

class Question {
    String question;
    String[] options;
    int correctAnswer;  

    // Constructor
    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    // Method to display the question and options
    public void displayQuestion(int currentQuestion, int totalQuestions) {
        System.out.println("\033[1;34m\n====== Question " + currentQuestion + " of " + totalQuestions + " ======\033[0m");
        System.out.println("\n" + question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.println("\033[1;33m\nYou have 7 seconds to answer...\033[0m");
    }

    // Method to check if the user's answer is correct
    public boolean isCorrect(int userAnswer) {
        return userAnswer == correctAnswer + 1;  // UserAnswer is 1-based
    }
}

public class Quiz {
    private static final int TIME_LIMIT = 8; // Time limit per question in seconds
    private static Question[] questions = new Question[5];
    private static int score = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeQuestions();
        ExecutorService executor = Executors.newSingleThreadExecutor();

        System.out.println("\033[1;36mWelcome to the Java Quiz Game!\033[0m");
        System.out.println("You have 7 seconds to answer each question. Let's begin!\n");

        for (int i = 0; i < questions.length; i++) {
            questions[i].displayQuestion(i + 1, questions.length);

            // Start a timer thread
            Future<Integer> userInput = executor.submit(() -> {
                return scanner.nextInt();
            });

            int answer = -1;
            try {
                answer = userInput.get(TIME_LIMIT, TimeUnit.SECONDS);  // Wait for user's input within the time limit
            } catch (TimeoutException e) {
                System.out.println("\033[1;31m\nTime's up! Moving to the next question.\033[0m");
                userInput.cancel(true);  // Cancel task if timeout occurs
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (answer != -1) {  // Only check the answer if input was received in time
                if (questions[i].isCorrect(answer)) {
                    System.out.println("\033[1;32mCorrect answer!\033[0m");
                    score++;
                } else {
                    System.out.println("\033[1;31mIncorrect answer!\033[0m");
                }
            }

            System.out.println("\033[1;34m==========================\033[0m\n");  // Divider for readability
        }

        executor.shutdown();
        displayResults();
        scanner.close();
    }

    // Method to initialize the Java-related quiz questions
    public static void initializeQuestions() {
        questions[0] = new Question("What is the default value of a local variable in Java?",
                new String[]{"null", "0", "undefined", "Local variables do not have a default value"}, 3);
        questions[1] = new Question("Which keyword is used to inherit a class in Java?",
                new String[]{"extends", "implements", "super", "import"}, 0);
        questions[2] = new Question("Which of these is a wrapper class in Java?",
                new String[]{"int", "Integer", "void", "float"}, 1);
        questions[3] = new Question("Which method is used to start a thread execution in Java?",
                new String[]{"start()", "run()", "init()", "resume()"}, 0);
        questions[4] = new Question("What does JVM stand for?",
                new String[]{"Java Verified Machine", "Java Virtual Machine", "Java Visual Machine", "Java Variable Machine"}, 1);
    }

    // Method to display the final score and results
    public static void displayResults() {
        System.out.println("\033[1;36m\n===== Quiz Results =====\033[0m");
        System.out.println("\033[1;35mTotal Questions: " + questions.length + "\033[0m");
        System.out.println("\033[1;32mCorrect Answers: " + score + "\033[0m");
        System.out.println("\033[1;31mIncorrect Answers: " + (questions.length - score) + "\033[0m");
        System.out.println("\033[1;36mYour Score: " + score + "/" + questions.length + "\033[0m");
    }
}
