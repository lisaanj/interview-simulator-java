public class TestSaveResult {
    public static void main(String[] args) {
        ResultDAO dao = new ResultDAO();
        boolean saved = dao.saveResult("Teste Banco", 14, "B2", "Excelente desempenho no simulador de entrevista.");
        if (!saved) {
            System.out.println("Save failed: " + dao.getLastErrorMessage());
        }
    }
}
