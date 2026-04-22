public class InterviewLogic {

    // Funcao que escolhe qual avaliacao usar baseado no tipo.
    public static Result evaluateAnswer(String type, String answer) {
        answer = answer.trim();

        if (answer.isEmpty()) {
            return new Result(
                    0,
                    "Voc\u00ea n\u00e3o respondeu.",
                    getSuggestion(type));
        }

        if (containsLowercasePronounError(answer)) {
            return new Result(
                    1,
                    "Boa tentativa! Em ingl\u00eas, o pronome 'I' deve ser escrito com letra mai\u00fascula.",
                    "Exemplo correto: " + capitalizeStandaloneI(answer));
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

    // Retorna sugestao baseada no tipo de pergunta.
    public static String getSuggestion(String type) {
        switch (type) {
            case "name":
                return "My name is Ana.";
            case "about":
                return "My name is Ana. I am 14 years old and I am a student.";
            case "job":
                return "I am a student.";
            case "reason":
                return "I want to work to learn and gain experience.";
            case "strengths":
                return "I am responsible and organized.";
            default:
                return "";
        }
    }

    public static Result evaluateName(String answer) {
        String lower = answer.toLowerCase().trim();
        int words = countWords(answer);

        if (answer.length() < 2) {
            return new Result(
                    0,
                    "Digite um nome v\u00e1lido.",
                    "My name is Ana.");
        }

        if (lower.startsWith("my name is")) {
            return new Result(
                    4,
                    "Muito bom! Resposta correta.",
                    "Sua resposta já está adequada.");
        }

        if (words == 1 || words == 2) {
            return new Result(
                    4,
                    "Muito bom! Seu nome foi informado corretamente.",
                    "Voc\u00ea tamb\u00e9m pode responder assim: My name is " + answer + ".");
        }

        return new Result(
                2,
                "Boa tentativa! Para essa pergunta, informe apenas seu nome.",
                "My name is Ana.");
    }

    public static Result evaluateAbout(String answer) {
        String lower = answer.toLowerCase();
        int words = countWords(answer);

        if (containsCommonMistakes(lower)) {
            return new Result(
                    2,
                    "Boa tentativa! Sua resposta tem a ideia certa, mas pode melhorar no ingl\u00eas.",
                    "My name is Ana. I am 14 years old and I am a student.");
        }

        if (words <= 2) {
            return new Result(
                    1,
                    "Boa tentativa! Tente falar um pouco mais sobre voc\u00ea.",
                    "I am 14 years old and I am a student.");
        }

        if ((lower.contains("i am") || lower.contains("my name is"))
                && (lower.contains("student") || lower.contains("years old"))) {
            if (words >= 6) {
                return new Result(
                        4,
                        "Muito bom! Voc\u00ea se apresentou de forma clara.",
                        "Sua resposta está clara e adequada.");
            }

            return new Result(
                    3,
                    "Boa resposta! Voc\u00ea pode acrescentar uma informa\u00e7\u00e3o a mais.",
                    "My name is Ana. I am 14 years old and I am a student.");
        }

        return new Result(
                2,
                "Boa tentativa! Tente usar frases simples sobre idade e estudo.",
                "I am 14 years old and I am a student.");
    }

    public static Result evaluateJob(String answer) {
        String lower = answer.toLowerCase();
        int words = countWords(answer);

        if (containsCommonMistakes(lower)) {
            return new Result(
                    2,
                    "Boa tentativa! Sua resposta foi entendida, mas pode ficar mais natural em inglês.",
                    "I am a student.");
        }

        if (words <= 2) {
            if (lower.contains("student")) {
                return new Result(
                        4,
                        "Boa resposta! Simples e correta.",
                        "Voc\u00ea tamb\u00e9m pode dizer: I am a student.");
            }

            return new Result(
                    1,
                    "Boa tentativa! Tente explicar melhor o que voc\u00ea faz.",
                    "I am a student.");
        }

        if (lower.contains("i am a student") || lower.contains("i am student")) {
            return new Result(
                    4,
                    "Muito bom! Essa é uma resposta simples e adequada.",
                    "Sua frase está boa.");
        }

        if (lower.contains("i study")) {
            return new Result(
                    3,
                    "Boa resposta! Voc\u00ea disse que estuda, o que faz sentido.",
                    "Voc\u00ea tamb\u00e9m pode dizer: I am a student.");
        }

        return new Result(
                2,
                "Boa tentativa! Para essa pergunta, respostas curtas e simples funcionam bem.",
                "I am a student.");
    }

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
                    "I want to work to learn and gain experience.");
        }

        if (lower.contains("because")
                && (lower.contains("learn") || lower.contains("help") || lower.contains("family"))) {
            if (words >= 7) {
                return new Result(
                        4,
                        "Muito bom! Seu motivo ficou claro.",
                        "Sua resposta está adequada.");
            }

            return new Result(
                    3,
                    "Boa resposta! Voc\u00ea explicou seu motivo.",
                    "I want to work because I want to learn and help my family.");
        }

        return new Result(
                2,
                "Boa tentativa! Tente explicar melhor o seu motivo.",
                "I want to work to learn and gain experience.");
    }

    public static Result evaluateStrengths(String answer) {
        String lower = answer.toLowerCase();
        int words = countWords(answer);

        if (containsCommonMistakes(lower)) {
            return new Result(
                    2,
                    "Boa tentativa! Vamos deixar a frase mais natural em ingl\u00eas.",
                    "I am responsible, organized, and friendly.");
        }

        if (words <= 2) {
            return new Result(
                    1,
                    "Boa tentativa! Tente citar mais qualidades.",
                    "I am responsible and organized.");
        }

        if (lower.contains("responsible") || lower.contains("organized")
                || lower.contains("friendly") || lower.contains("dedicated")
                || lower.contains("kind") || lower.contains("communicative")) {

            if (words >= 4) {
                return new Result(
                        4,
                        "Muito bom! Você citou qualidades importantes.",
                        "Sua resposta está adequada.");
            }

            return new Result(
                    3,
                    "Boa resposta! Voc\u00ea pode acrescentar mais uma qualidade.",
                    "I am responsible, organized, and friendly.");
        }

        return new Result(
                2,
                "Boa tentativa! Tente usar qualidades simples em ingl\u00eas.",
                "I am responsible, organized, and friendly.");
    }

    public static int countWords(String text) {
        if (text.trim().isEmpty()) {
            return 0;
        }
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    public static boolean containsLowercasePronounError(String text) {
        return text.matches(".*\\bi\\b.*");
    }

    public static String capitalizeStandaloneI(String text) {
        return text.replaceAll("\\bi\\b", "I");
    }

    // Detecta alguns erros comuns para feedback educativo.
    public static boolean containsCommonMistakes(String text) {
        text = text.toLowerCase();

        return text.contains("i 1m")
                || text.contains("iam")

                || text.contains("i student")
                || text.contains("i auxiliar")
                || text.contains("i very")
                || text.contains("i ready")

                || text.contains("i 20 years")
                || text.contains("i 20 year")
                || text.contains("i have") && text.contains("years")
                || text.contains("years olds")

                || text.contains("a students")
                || text.contains("i am student")

                || text.contains("job in")
                || text.contains("work like")

                || text.contains("want work")
                || text.contains("like work")
                || text.contains("need work")

                || text.contains("because learn")

                || text.contains("my name ana")
                || text.contains("my name is is")

                || text.contains("auxiliar")
                || text.contains("mercado")
                || text.contains("estudante")

                || text.contains("make faculdade")
                || text.contains("do internship")
                || text.contains("course of")

                || text.contains("many informations")
                || text.contains("two year")

                || text.equals("because yes")
                || text.equals("i want")
                || text.equals("i like");
    }
}
