import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultDAO {
    private String lastErrorMessage;

    public boolean initializeDatabase() {
        lastErrorMessage = null;

        try (Connection conn = DatabaseConnection.connect()) {
            ensureSchema(conn);
            return true;
        } catch (SQLException e) {
            lastErrorMessage = e.getMessage();
            System.out.println("Error initializing database:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveResult(String name, int score, String englishLevel, String feedback) {
        return saveResult(name, score, englishLevel, feedback, "Respostas nao informadas.");
    }

    public boolean saveResult(String name, int score, String englishLevel, String feedback, String answersSummary) {
        String sql = "INSERT INTO resultados (nome, score, nivel_ingles, feedback, respostas) VALUES (?, ?, ?, ?, ?)";
        lastErrorMessage = null;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ensureSchema(conn);

            stmt.setString(1, name);
            stmt.setInt(2, score);
            stmt.setString(3, englishLevel);
            stmt.setString(4, feedback);
            stmt.setString(5, answersSummary);

            stmt.executeUpdate();
            System.out.println("Result saved successfully!");
            return true;

        } catch (SQLException e) {
            lastErrorMessage = e.getMessage();
            System.out.println("Error saving result:");
            e.printStackTrace();
            return false;
        }
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public List<HistoryEntry> listResults() {
        String sql = "SELECT nome, score, nivel_ingles, feedback, respostas, "
                + "DATE_FORMAT(data_tentativa, '%d/%m/%Y %H:%i') AS data_formatada "
                + "FROM resultados ORDER BY data_tentativa DESC LIMIT 20";
        lastErrorMessage = null;
        List<HistoryEntry> history = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = prepareHistoryStatement(conn, sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                history.add(new HistoryEntry(
                        rs.getString("nome"),
                        rs.getInt("score"),
                        rs.getString("nivel_ingles"),
                        rs.getString("data_formatada"),
                        rs.getString("feedback"),
                        rs.getString("respostas")));
            }

        } catch (SQLException e) {
            lastErrorMessage = e.getMessage();
            System.out.println("Error loading history:");
            e.printStackTrace();
        }

        return history;
    }

    public boolean clearHistory() {
        String sql = "DELETE FROM resultados";
        lastErrorMessage = null;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ensureSchema(conn);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            lastErrorMessage = e.getMessage();
            System.out.println("Error clearing history:");
            e.printStackTrace();
            return false;
        }
    }

    private PreparedStatement prepareHistoryStatement(Connection conn, String sql) throws SQLException {
        ensureSchema(conn);
        return conn.prepareStatement(sql);
    }

    private void ensureSchema(Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS resultados ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "nome VARCHAR(255) NOT NULL,"
                        + "score INT NOT NULL,"
                        + "nivel_ingles VARCHAR(10) NOT NULL,"
                        + "feedback TEXT,"
                        + "data_tentativa DATETIME DEFAULT CURRENT_TIMESTAMP,"
                        + "respostas TEXT"
                        + ")")) {
            stmt.executeUpdate();
        }

        if (!columnExists(conn, "resultados", "data_tentativa")) {
            try (PreparedStatement stmt = conn.prepareStatement(
                    "ALTER TABLE resultados ADD COLUMN data_tentativa DATETIME DEFAULT CURRENT_TIMESTAMP")) {
                stmt.executeUpdate();
            }
        }

        if (!columnExists(conn, "resultados", "respostas")) {
            try (PreparedStatement stmt = conn.prepareStatement(
                    "ALTER TABLE resultados ADD COLUMN respostas TEXT")) {
                stmt.executeUpdate();
            }
        }
    }

    private boolean columnExists(Connection conn, String tableName, String columnName) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();

        try (ResultSet rs = metaData.getColumns(conn.getCatalog(), null, tableName, columnName)) {
            return rs.next();
        }
    }
}
