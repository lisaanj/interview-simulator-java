public class HistoryEntry {
    private final String name;
    private final int score;
    private final String englishLevel;
    private final String date;
    private final String feedback;
    private final String answersSummary;

    public HistoryEntry(String name, int score, String englishLevel, String date, String feedback, String answersSummary) {
        this.name = name;
        this.score = score;
        this.englishLevel = englishLevel;
        this.date = date;
        this.feedback = feedback;
        this.answersSummary = answersSummary;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getEnglishLevel() {
        return englishLevel;
    }

    public String getDate() {
        return date;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getAnswersSummary() {
        return answersSummary;
    }
}
