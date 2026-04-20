import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int score = 0;
        int maxScore = 20;

        System.out.println("=== SIMULADOR DE ENTREVISTA EM INGLES ===");
        System.out.println("Responda em ingles. O feedback aparecera em portugues.\n");

        System.out.println("1. What is your name?");
        String nameAnswer = scanner.nextLine();
        String name = extractCandidateName(nameAnswer);
        Result r1 = InterviewLogic.evaluateAnswer("name", nameAnswer);
        score += r1.getScore();
        System.out.println("Feedback: " + r1.getFeedback());
        System.out.println("Sugestao: " + r1.getSuggestion());

        System.out.println("\n2. Tell me about yourself:");
        String about = scanner.nextLine();
        Result r2 = InterviewLogic.evaluateAnswer("about", about);
        score += r2.getScore();
        System.out.println("Feedback: " + r2.getFeedback());
        System.out.println("Sugestao: " + r2.getSuggestion());

        System.out.println("\n3. What do you do?");
        String job = scanner.nextLine();
        Result r3 = InterviewLogic.evaluateAnswer("job", job);
        score += r3.getScore();
        System.out.println("Feedback: " + r3.getFeedback());
        System.out.println("Sugestao: " + r3.getSuggestion());

        System.out.println("\n4. Why do you want to work?");
        String reason = scanner.nextLine();
        Result r4 = InterviewLogic.evaluateAnswer("reason", reason);
        score += r4.getScore();
        System.out.println("Feedback: " + r4.getFeedback());
        System.out.println("Sugestao: " + r4.getSuggestion());

        System.out.println("\n5. What are your strengths?");
        String strengths = scanner.nextLine();
        Result r5 = InterviewLogic.evaluateAnswer("strengths", strengths);
        score += r5.getScore();
        System.out.println("Feedback: " + r5.getFeedback());
        System.out.println("Sugestao: " + r5.getSuggestion());

        System.out.println("\n===== RESUMO DA ENTREVISTA =====");
        System.out.println("Nome: " + name);
        System.out.println("Sobre voce: " + about);
        System.out.println("O que faz: " + job);
        System.out.println("Motivo para trabalhar: " + reason);
        System.out.println("Qualidades: " + strengths);

        double percentage = (score * 100.0) / maxScore;
        String englishLevel;
        String nivel;
        String feedbackFinal;

        if (score <= 5) {
            englishLevel = "A1";
            nivel = "Iniciante";
            feedbackFinal = "Voc\u00ea est\u00e1 come\u00e7ando. O importante \u00e9 tentar responder.";
        } else if (score <= 10) {
            englishLevel = "A2";
            nivel = "Basico";
            feedbackFinal = "Boa tentativa! Continue praticando frases simples em ingl\u00eas.";
        } else if (score <= 15) {
            englishLevel = "B1";
            nivel = "Bom";
            feedbackFinal = "Muito bom! Suas respostas foram claras e adequadas.";
        } else {
            englishLevel = "B2";
            nivel = "Excelente";
            feedbackFinal = "Excelente! Voc\u00ea respondeu muito bem para uma entrevista b\u00e1sica.";
        }

        System.out.println("\n===== RESULTADO FINAL =====");
        System.out.println("Pontua\u00e7\u00e3o: " + score + " de " + maxScore + " pontos");
        System.out.printf("Aproveitamento: %.1f%%%n", percentage);
        System.out.println("Nivel: " + nivel + " (" + englishLevel + ")");
        System.out.println("Feedback geral: " + feedbackFinal);

        System.out.println("\nDica final: Em entrevistas simples, tente responder com frases curtas, claras e corretas.");
        System.out.println("Exemplo: My name is Ana. I am 14 years old. I am a student.");

        scanner.close();
    }

    private static String extractCandidateName(String answer) {
        String cleaned = answer.trim();
        String lower = cleaned.toLowerCase();

        if (lower.startsWith("my name is ")) {
            cleaned = cleaned.substring(11).trim();
        } else if (lower.startsWith("i am ")) {
            cleaned = cleaned.substring(5).trim();
        } else if (lower.startsWith("i'm ")) {
            cleaned = cleaned.substring(4).trim();
        }

        cleaned = cleaned.replaceAll("[.!?,;:]+$", "").trim();
        cleaned = cleaned.replaceAll("[^\\p{L}\\s'-]", " ").trim();
        cleaned = cleaned.replaceAll("\\s+", " ").trim();

        if (cleaned.isBlank()) {
            return "Sem nome";
        }

        String[] words = cleaned.split(" ");
        if (words.length == 1) {
            return capitalizeName(cleaned);
        }

        StringBuilder nameBuilder = new StringBuilder();
        int added = 0;

        for (String word : words) {
            if (word.isBlank()) {
                continue;
            }

            String normalized = word.toLowerCase();
            if (normalized.equals("student") || normalized.equals("years") || normalized.equals("old")
                    || normalized.equals("responsible") || normalized.equals("organized")) {
                break;
            }

            if (added > 0) {
                nameBuilder.append(" ");
            }
            nameBuilder.append(capitalizeName(word));
            added++;

            if (added == 2) {
                break;
            }
        }

        if (nameBuilder.length() == 0) {
            return "Sem nome";
        }

        return nameBuilder.toString();
    }

    private static String capitalizeName(String text) {
        if (text.isBlank()) {
            return text;
        }

        String lower = text.toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }
}
