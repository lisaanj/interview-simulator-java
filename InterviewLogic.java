public class InterviewLogic {

    // Funcao que escolhe qual avaliacao usar baseado no tipo
    public static Result evaluateAnswer(String type, String answer) {
        answer = answer.trim();

        if (answer.isEmpty()) {
            return new Result(
                    0,
                    "Você não respondeu.",
                    getSuggestion(type));
        }

        switch (type) {
            case "name":
                return evaluateName(answer);
            case "about":
                return evaluateAbout(answer);
            case "job":
                return evaluateJob(answer);
            case "reason":
                return evaluateReason(answer);
            case "strengths":
                return evaluateStrengths(answer);
            default:
                return new Result(0, "Tipo desconhecido", "N/A");
        }
    }

    // Retorna sugestao baseada no tipo de pergunta
    public static String getSuggestion(String type) {
        switch (type) {
            case "name":
                return "My name is Ana.";
            case "about":
                return "My name is Ana. I am 14 years old and I am a student.";
            case "job":
                return "I am a student.";
            case "reason":
                return "I want to work because I want to learn.";
            case "strengths":
                return "I am responsible and organized.";
            default:
                return "";
        }
    }

    // Avaliacao da pergunta nome
    public static Result evaluateName(String answer) {
        String lower = answer.toLowerCase().trim();
        int words = countWords(answer);

        if (answer.length() < 2) {
            return new Result(
                    0,
                    "Digite um nome válido.",
                    "My name is Ana.");
        }

        if (lower.startsWith("my name is")) {
            return new Result(
                    4,
                    "Muito bom! Resposta correta.",
                    "Sua resposta ja esta adequada.");
        }

        if (words == 1 || words == 2) {
            return new Result(
                    4,
                    "Muito bom! Seu nome foi informado corretamente.",
                    "Você também pode responder assim: My name is " + answer + ".");
        }

        return new Result(
                2,
                "Boa tentativa! Para essa pergunta, informe apenas seu nome.",
                "My name is Ana.");
    }

    // Avaliacao da pergunta "Tell me about yourself"
    public static Result evaluateAbout(String answer) {
        String lower = answer.toLowerCase();
        int words = countWords(answer);

        if (containsCommonMistakes(lower)) {
            return new Result(
                    2,
                    "Boa tentativa! Sua resposta tem a ideia certa, mas pode melhorar no inglês.",
                    "My name is Ana. I am 14 years old and I am a student.");
        }

        if (words <= 2) {
            return new Result(
                    1,
                    "Boa tentativa! Tente falar um pouco mais sobre você.",
                    "I am 14 years old and I am a student.");
        }

        if ((lower.contains("i am") || lower.contains("my name is")) &&
                (lower.contains("student") || lower.contains("years old"))) {
            if (words >= 6) {
                return new Result(
                        4,
                        "Muito bom! Você se apresentou de forma clara.",
                        "Sua resposta está adequada para o nível básico.");
            } else {
                return new Result(
                        3,
                        "Boa resposta! Você pode acrescentar uma informação a mais.",
                        "My name is Ana. I am 14 years old and I am a student.");
            }
        }

        return new Result(
                2,
                "Boa tentativa! Tente usar frases simples sobre idade e estudo.",
                "I am 14 years old and I am a student.");
    }

    // Avaliacao da pergunta "What do you do?"
    public static Result evaluateJob(String answer) {
        String lower = answer.toLowerCase();
        int words = countWords(answer);

        if (containsCommonMistakes(lower)) {
            return new Result(
                    2,
                    "Sua resposta foi entendida, mas pode ficar mais natural em inglês.",
                    "I am a student.");
        }

        if (words <= 2) {
            if (lower.contains("student")) {
                return new Result(
                        4,
                        "Boa resposta! Simples e correta.",
                        "Você também pode dizer: I am a student.");
            }

            return new Result(
                    1,
                    "Boa tentativa! Tente explicar melhor o que você faz.",
                    "I am a student.");
        }

        if (lower.contains("i am a student") || lower.contains("i am student")) {
            return new Result(
                    4,
                    "Muito bom! Essa e uma resposta adequada para sua idade.",
                    "Sua frase esta boa.");
        }

        if (lower.contains("i study")) {
            return new Result(
                    3,
                    "Boa resposta! Você disse que estuda, o que faz sentido.",
                    "Você também pode dizer: I am a student.");
        }

        return new Result(
                2,
                "Boa tentativa! Para essa pergunta, respostas curtas e simples funcionam bem.",
                "I am a student.");
    }

    // Avaliacao da pergunta "Why do you want to work?"
    public static Result evaluateReason(String answer) {
        String lower = answer.toLowerCase();
        int words = countWords(answer);

        if (containsCommonMistakes(lower)) {
            return new Result(
                    2,
                    "Boa tentativa! Sua ideia foi entendida, mas a frase pode melhorar.",
                    "I want to work because I want to learn and help my family.");
        }

        if (words <= 3) {
            return new Result(
                    1,
                    "Boa tentativa! Tente explicar o motivo com um pouco mais de detalhes.",
                    "I want to work because I want to learn.");
        }

        if (lower.contains("because")) {
            if (lower.contains("learn") || lower.contains("help") || lower.contains("family")) {
                if (words >= 7) {
                    return new Result(
                            4,
                            "Muito bom! Seu motivo ficou claro.",
                            "Sua resposta esta adequada.");
                } else {
                    return new Result(
                            3,
                            "Boa resposta! Você explicou seu motivo.",
                            "I want to work because I want to learn and help my family.");
                }
            }
        }

        return new Result(
                2,
                "Boa tentativa! Tente usar 'because' para explicar seu motivo.",
                "I want to work because I want to learn.");
    }

    // Avaliacao da pergunta "What are your strengths?"
    public static Result evaluateStrengths(String answer) {
        String lower = answer.toLowerCase();
        int words = countWords(answer);

        if (containsCommonMistakes(lower)) {
            return new Result(
                    2,
                    "Boa tentativa! Vamos deixar a frase mais natural em inglês.",
                    "I am responsible, organized, and friendly.");
        }

        if (words <= 2) {
            return new Result(
                    1,
                    "Boa tentativa! Tente citar mais qualidades.",
                    "I am responsible and organized.");
        }

        if (lower.contains("responsible") || lower.contains("organized") ||
                lower.contains("friendly") || lower.contains("dedicated") ||
                lower.contains("kind") || lower.contains("communicative")) {

            if (words >= 4) {
                return new Result(
                        4,
                        "Muito bom! Voce citou qualidades importantes.",
                        "Sua resposta esta adequada.");
            } else {
                return new Result(
                        3,
                        "Boa resposta! Você pode acrescentar mais uma qualidade.",
                        "I am responsible, organized, and friendly.");
            }
        }

        return new Result(
                2,
                "Boa tentativa! Tente usar qualidades simples em inglês.",
                "I am responsible, organized, and friendly.");
    }

    // Conta quantas palavras existem na resposta
    public static int countWords(String text) {
        if (text.trim().isEmpty()) {
            return 0;
        }
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    // Detecta alguns erros comuns para feedback educativo
    public static boolean containsCommonMistakes(String text) {
        text = text.toLowerCase();

        return text.contains("i 1m") ||
                text.contains("iam") ||

                // Falta do verbo to be
                text.contains("i student") ||
                text.contains("i auxiliar") ||
                text.contains("i very") ||
                text.contains("i ready") ||

                // Idade errada
                text.contains("i 20 years") ||
                text.contains("i 20 year") ||
                text.contains("i have") && text.contains("years") ||
                text.contains("years olds") ||

                // Artigos errados
                text.contains("a students") ||
                text.contains("i am student") ||

                // Trabalho
                text.contains("job in") ||
                text.contains("work like") ||

                // Falta do "to"
                text.contains("want work") ||
                text.contains("like work") ||
                text.contains("need work") ||

                // Because errado
                text.contains("because learn") ||

                // Nome errado
                text.contains("my name ana") ||
                text.contains("my name is is") ||

                // Mistura português + inglês
                text.contains("auxiliar") ||
                text.contains("mercado") ||
                text.contains("estudante") ||

                // Tradução literal
                text.contains("make faculdade") ||
                text.contains("do internship") ||
                text.contains("course of") ||

                // Plural
                text.contains("many informations") ||
                text.contains("two year") ||

                // Respostas muito fracas
                text.equals("because yes") ||
                text.equals("i want") ||
                text.equals("i like");
    }
}
