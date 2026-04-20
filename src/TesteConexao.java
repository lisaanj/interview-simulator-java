import java.sql.Connection;
import java.sql.DriverManager;

public class TesteConexao {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/interview_simulator";
        String user = "root";
        String password = "root123";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexao realizada com sucesso!");
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao conectar:");
            e.printStackTrace();
        }
    }
}
