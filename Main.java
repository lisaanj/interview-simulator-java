import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int score = 0;
        int maxScore = 20;

        System.out.println("=== SIMULADOR DE ENTREVISTA EM INGLES ===");
        System.out.println("Responda em ingles. O feedback aparecera em portugues.\n");

        // 1. Nome
        System.out.println("1. What is your name?");
        String name = scanner.nextLine();
        Result r1 = InterviewLogic.evaluateAnswer("name", name);
        score += r1.score;
        System.out.println("Feedback: " + r1.feedback);
        System.out.println("Sugestao: " + r1.suggestion);

        // 2. Sobre voce
        System.out.println("\n2. Tell me about yourself:");
        String about = scanner.nextLine();
        Result r2 = InterviewLogic.evaluateAnswer("about", about);
        score += r2.score;
        System.out.println("Feedback: " + r2.feedback);
        System.out.println("Sugestao: " + r2.suggestion);

        // 3. O que faz
        System.out.println("\n3. What do you do?");
        String job = scanner.nextLine();
        Result r3 = InterviewLogic.evaluateAnswer("job", job);
        score += r3.score;
        System.out.println("Feedback: " + r3.feedback);
        System.out.println("Sugestao: " + r3.suggestion);

        // 4. Por que quer trabalhar
        System.out.println("\n4. Why do you want to work?");
        String reason = scanner.nextLine();
        Result r4 = InterviewLogic.evaluateAnswer("reason", reason);
        score += r4.score;
        System.out.println("Feedback: " + r4.feedback);
        System.out.println("Sugestao: " + r4.suggestion);

        // 5. Qualidades
        System.out.println("\n5. What are your strengths?");
        String strengths = scanner.nextLine();
        Result r5 = InterviewLogic.evaluateAnswer("strengths", strengths);
        score += r5.score;
        System.out.println("Feedback: " + r5.feedback);
        System.out.println("Sugestao: " + r5.suggestion);

        // Resumo final
        System.out.println("\n===== RESUMO DA ENTREVISTA =====");
        System.out.println("Nome: " + name);
        System.out.println("Sobre voce: " + about);
        System.out.println("O que faz: " + job);
        System.out.println("Motivo para trabalhar: " + reason);
        System.out.println("Qualidades: " + strengths);

        // Calculo da porcentagem
        double percentage = (score * 100.0) / maxScore;

        // Resultado final
        System.out.println("\n===== RESULTADO FINAL =====");
        System.out.println("Pontuacao: " + score + " de " + maxScore + " pontos");
        System.out.printf("Aproveitamento: %.1f%%\n", percentage);

        if (percentage < 30) {
            System.out.println("Nivel: Iniciante");
            System.out.println("Feedback geral: Voce esta comecando. O importante e tentar responder.");
        } else if (percentage < 60) {
            System.out.println("Nivel: Basico");
            System.out.println("Feedback geral: Boa tentativa! Continue praticando frases simples em ingles.");
        } else if (percentage < 85) {
            System.out.println("Nivel: Bom");
            System.out.println("Feedback geral: Muito bom! Suas respostas foram claras e adequadas.");
        } else {
            System.out.println("Nivel: Excelente");
            System.out.println("Feedback geral: Excelente! Voce respondeu muito bem para uma entrevista basica.");
        }

        // Feedback final personalizado
        System.out.println("\nDica final: Em entrevistas simples, tente responder com frases curtas, claras e corretas.");
        System.out.println("Exemplo: My name is Ana. I am 14 years old. I am a student.");

        scanner.close();
    }
}
