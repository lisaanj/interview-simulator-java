import java.util.Scanner;

public class InterviewSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Interview Simulator ===\n");

        int score = 0;

        // 1. Nome
        System.out.println("What is your name?");
        String name = scanner.nextLine();
        score += evaluateAnswer(name);

        // 2. Sobre você
        System.out.println("Tell me about yourself:");
        String about = scanner.nextLine();
        score += evaluateAnswer(about);

        // 3. O que faz
        System.out.println("What do you do?");
        String job = scanner.nextLine();
        score += evaluateAnswer(job);

        // 4. Por que quer trabalhar
        System.out.println("Why do you want to work?");
        String reason = scanner.nextLine();
        score += evaluateAnswer(reason);

        // 5. Qualidades
        System.out.println("What are your strengths?");
        String strengths = scanner.nextLine();
        score += evaluateAnswer(strengths);

        // Resumo
        System.out.println("\n===== INTERVIEW SUMMARY =====");
        System.out.println("Name: " + name);
        System.out.println("About: " + about);
        System.out.println("Job: " + job);
        System.out.println("Reason: " + reason);
        System.out.println("Strengths: " + strengths);

        // Pontuação final
        System.out.println("\nFinal score: " + score + " points");

        if (score <= 5) {
            System.out.println("Overall feedback: Keep practicing your answers.");
        } else if (score <= 10) {
            System.out.println("Overall feedback: Good job! You are doing well.");
        } else {
            System.out.println("Overall feedback: Excellent! Your answers were well developed.");
        }

        scanner.close();
    }

    // Função para avaliar resposta
    public static int evaluateAnswer(String answer) {
        int length = answer.trim().length();

        if (length == 0) {
            System.out.println("Feedback: You did not answer.");
            return 0;
        } else if (length < 10) {
            System.out.println("Feedback: Very short answer. Try to add more details.");
            return 1;
        } else if (length < 25) {
            System.out.println("Feedback: Good answer!");
            return 2;
        } else {
            System.out.println("Feedback: Great answer!");
            return 3;
        }
    }
}
