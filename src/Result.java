public class Result {
    private final int score;
    private final String feedback;
    private final String suggestion;
    private final String englishLevel;

    public Result(int score, String feedback, String suggestion) {
        this(score, feedback, suggestion, determineEnglishLevel(score));
    }

    public Result(int score, String feedback, String suggestion, String englishLevel) {
        this.score = score;
        this.feedback = feedback;
        this.suggestion = suggestion;
        this.englishLevel = englishLevel;
    }

    public int getScore() {
        return score;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public String getEnglishLevel() {
        return englishLevel;
    }

    private static String determineEnglishLevel(int score) {
        if (score <= 1) {
            return "A1";
        }
        if (score == 2) {
            return "A2";
        }
        if (score == 3) {
            return "B1";
        }
        return "B2";
    }
}
