import java.util.Scanner;

public class InterviewSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Interview Simulator ===\n");

        System.out.println("What is your name?");
        String name = scanner.nextLine();

        System.out.println("What do you do?");
        String job = scanner.nextLine();

        System.out.println("Why do you want to work?");
        String reason = scanner.nextLine();

        System.out.println("Tell me about yourself:");
        String about = scanner.nextLine();

        System.out.println("What are your strengths?");
        String strengths = scanner.nextLine();

        System.out.println("\n-----------------------------");
        System.out.println("--- Summary ---");

        System.out.println("Name: " + name);
        System.out.println("Job: " + job);
        System.out.println("Reason: " + reason);
        System.out.println("About: " + about);
        System.out.println("Strengths: " + strengths);

        scanner.close();
    }
}

