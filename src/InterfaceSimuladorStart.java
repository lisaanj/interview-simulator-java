import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class InterfaceSimuladorStart {

    private int score = 0;
    private int perguntaAtual = 0;
    private String candidateName = "";
    private final String[] respostasUsuario = new String[5];

    private final String[] perguntas = {
            "What is your name?",
            "Tell me about yourself:",
            "What do you do?",
            "Why do you want to work?",
            "What are your strengths?"
    };

    private final String[] tipos = {
            "name", "about", "job", "reason", "strengths"
    };

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JLabel progressoLabel;
    private JProgressBar barraProgresso;
    private JTextArea perguntaArea;
    private JTextArea respostaArea;
    private JLabel feedbackLabel;
    private JButton botaoProximo;

    public InterfaceSimuladorStart() {
        criarInterface();
    }

    private void criarInterface() {
        frame = new JFrame("Interview Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 600);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(criarTelaInicial(), "inicio");
        mainPanel.add(criarTelaEntrevista(), "entrevista");

        frame.setContentPane(mainPanel);
        frame.setVisible(true);

        cardLayout.show(mainPanel, "inicio");
    }

    private JPanel criarTelaInicial() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(new Color(244, 241, 255));
        painel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setOpaque(false);

        JLabel titulo = new JLabel("Interview Simulator");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 34));
        titulo.setForeground(new Color(78, 56, 160));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Practice basic job interview answers in English");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 17));
        subtitulo.setForeground(new Color(108, 92, 160));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea descricao = new JTextArea(
                "This simulator helps students practice simple interview questions in English.\n\n"
                        + "You will answer 5 questions.\n"
                        + "After each answer, the system will show feedback and a suggestion.\n\n"
                        + "Click the button below to begin.");
        descricao.setEditable(false);
        descricao.setFocusable(false);
        descricao.setFont(new Font("SansSerif", Font.PLAIN, 16));
        descricao.setForeground(new Color(55, 48, 90));
        descricao.setBackground(Color.WHITE);
        descricao.setLineWrap(true);
        descricao.setWrapStyleWord(true);
        descricao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 214, 245), 1),
                new EmptyBorder(20, 20, 20, 20)));
        descricao.setMaximumSize(new Dimension(620, 180));
        descricao.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("Start Interview");
        startButton.setFont(new Font("SansSerif", Font.BOLD, 17));
        startButton.setBackground(new Color(124, 92, 255));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setPreferredSize(new Dimension(180, 46));
        startButton.setMaximumSize(new Dimension(220, 46));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setBorder(BorderFactory.createEmptyBorder());

        startButton.addActionListener(e -> cardLayout.show(mainPanel, "entrevista"));

        JButton historyButton = new JButton("View History");
        historyButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        historyButton.setBackground(Color.WHITE);
        historyButton.setForeground(new Color(92, 70, 156));
        historyButton.setFocusPainted(false);
        historyButton.setPreferredSize(new Dimension(180, 44));
        historyButton.setMaximumSize(new Dimension(220, 44));
        historyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        historyButton.setBorder(BorderFactory.createLineBorder(new Color(200, 188, 245), 1));
        historyButton.addActionListener(e -> showHistoryDialog());

        centro.add(Box.createVerticalGlue());
        centro.add(titulo);
        centro.add(Box.createVerticalStrut(10));
        centro.add(subtitulo);
        centro.add(Box.createVerticalStrut(25));
        centro.add(descricao);
        centro.add(Box.createVerticalStrut(25));
        centro.add(startButton);
        centro.add(Box.createVerticalStrut(12));
        centro.add(historyButton);
        centro.add(Box.createVerticalGlue());

        painel.add(centro, BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarTelaEntrevista() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(20, 20));
        painelPrincipal.setBackground(new Color(245, 244, 252));
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel painelTopo = new JPanel();
        painelTopo.setLayout(new BoxLayout(painelTopo, BoxLayout.Y_AXIS));
        painelTopo.setBackground(new Color(124, 92, 255));
        painelTopo.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel tituloLabel = new JLabel("Interview Simulator");
        tituloLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtituloLabel = new JLabel("Answer the questions in English");
        subtituloLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        subtituloLabel.setForeground(new Color(232, 226, 255));
        subtituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        progressoLabel = new JLabel("Question 1 of 5");
        progressoLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        progressoLabel.setForeground(Color.WHITE);
        progressoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        barraProgresso = new JProgressBar(0, 5);
        barraProgresso.setValue(1);
        barraProgresso.setStringPainted(false);
        barraProgresso.setForeground(Color.WHITE);
        barraProgresso.setBackground(new Color(166, 144, 255));
        barraProgresso.setMaximumSize(new Dimension(320, 10));
        barraProgresso.setPreferredSize(new Dimension(320, 10));
        barraProgresso.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelTopo.add(tituloLabel);
        painelTopo.add(Box.createVerticalStrut(6));
        painelTopo.add(subtituloLabel);
        painelTopo.add(Box.createVerticalStrut(12));
        painelTopo.add(progressoLabel);
        painelTopo.add(Box.createVerticalStrut(8));
        painelTopo.add(barraProgresso);

        JPanel painelCentro = new JPanel(new BorderLayout());
        painelCentro.setOpaque(false);

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 221, 245), 1),
                new EmptyBorder(22, 22, 22, 22)));

        JLabel perguntaTitulo = new JLabel("Question");
        perguntaTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        perguntaTitulo.setForeground(new Color(75, 61, 117));
        perguntaTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        perguntaArea = new JTextArea(perguntas[0]);
        perguntaArea.setFont(new Font("SansSerif", Font.PLAIN, 21));
        perguntaArea.setForeground(new Color(40, 34, 66));
        perguntaArea.setBackground(Color.WHITE);
        perguntaArea.setEditable(false);
        perguntaArea.setFocusable(false);
        perguntaArea.setLineWrap(true);
        perguntaArea.setWrapStyleWord(true);
        perguntaArea.setBorder(new EmptyBorder(8, 0, 12, 0));
        perguntaArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel respostaTitulo = new JLabel("Your answer");
        respostaTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        respostaTitulo.setForeground(new Color(75, 61, 117));
        respostaTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        respostaArea = new JTextArea();
        respostaArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        respostaArea.setForeground(new Color(35, 35, 35));
        respostaArea.setLineWrap(true);
        respostaArea.setWrapStyleWord(true);
        respostaArea.setBorder(new EmptyBorder(14, 14, 14, 14));
        respostaArea.setBackground(new Color(250, 249, 255));
        respostaArea.setCaretColor(new Color(60, 60, 60));

        JScrollPane respostaScroll = new JScrollPane(respostaArea);
        respostaScroll.setPreferredSize(new Dimension(690, 150));
        respostaScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        respostaScroll.setBorder(BorderFactory.createLineBorder(new Color(215, 210, 240), 1));
        respostaScroll.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel feedbackPanel = new JPanel(new BorderLayout());
        feedbackPanel.setBackground(new Color(244, 240, 255));
        feedbackPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(215, 200, 255), 1),
                new EmptyBorder(12, 12, 12, 12)));
        feedbackPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        feedbackLabel = new JLabel("Write your answer and click Next.");
        feedbackLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        feedbackLabel.setForeground(new Color(92, 70, 156));

        feedbackPanel.add(feedbackLabel, BorderLayout.CENTER);

        card.add(perguntaTitulo);
        card.add(Box.createVerticalStrut(6));
        card.add(perguntaArea);
        card.add(Box.createVerticalStrut(12));
        card.add(respostaTitulo);
        card.add(Box.createVerticalStrut(8));
        card.add(respostaScroll);
        card.add(Box.createVerticalStrut(16));
        card.add(feedbackPanel);

        painelCentro.add(card, BorderLayout.CENTER);

        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelRodape.setOpaque(false);

        botaoProximo = new JButton("Next");
        botaoProximo.setFont(new Font("SansSerif", Font.BOLD, 16));
        botaoProximo.setFocusPainted(false);
        botaoProximo.setBackground(new Color(124, 92, 255));
        botaoProximo.setForeground(Color.WHITE);
        botaoProximo.setPreferredSize(new Dimension(140, 44));
        botaoProximo.setBorder(BorderFactory.createEmptyBorder());
        botaoProximo.addActionListener(e -> processarResposta());

        painelRodape.add(botaoProximo);

        painelPrincipal.add(painelTopo, BorderLayout.NORTH);
        painelPrincipal.add(painelCentro, BorderLayout.CENTER);
        painelPrincipal.add(painelRodape, BorderLayout.SOUTH);

        return painelPrincipal;
    }

    private void processarResposta() {
        String resposta = respostaArea.getText().trim();

        if (resposta.isEmpty()) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Write an answer before continuing.",
                    "Answer Required",
                    JOptionPane.WARNING_MESSAGE);
            respostaArea.requestFocusInWindow();
            return;
        }

        Result r = InterviewLogic.evaluateAnswer(tipos[perguntaAtual], resposta);
        score += r.getScore();
        respostasUsuario[perguntaAtual] = resposta;

        if (perguntaAtual == 0) {
            candidateName = extractCandidateName(resposta);
        }

        feedbackLabel.setText(
                "<html><b>Feedback:</b> " + r.getFeedback()
                        + "<br><b>Suggestion:</b> " + r.getSuggestion() + "</html>");

        perguntaAtual++;

        if (perguntaAtual < perguntas.length) {
            perguntaArea.setText(perguntas[perguntaAtual]);
            progressoLabel.setText("Question " + (perguntaAtual + 1) + " of 5");
            barraProgresso.setValue(perguntaAtual + 1);
            respostaArea.setText("");
        } else {
            mostrarResultadoFinal();
        }
    }

    private void mostrarResultadoFinal() {
        double percentage = (score * 100.0) / 20.0;
        String englishLevel = determineFinalEnglishLevel();
        String nivel = determineFinalLabel(englishLevel);
        String feedbackFinal = determineFinalFeedback(englishLevel);
        String answersSummary = buildAnswersSummary();

        ResultDAO dao = new ResultDAO();
        String savedName = candidateName.isBlank() ? "Sem nome" : candidateName;
        boolean saved = dao.saveResult(savedName, score, englishLevel, feedbackFinal, answersSummary);
        String saveStatus = saved
                ? "Resultado salvo no banco com sucesso."
                : "Nao foi possivel salvar no banco: " + dao.getLastErrorMessage();

        JPanel resultPanel = new JPanel(new BorderLayout(10, 10));
        resultPanel.setBackground(new Color(248, 245, 255));
        resultPanel.setBorder(new EmptyBorder(18, 18, 18, 18));

        JLabel resultTitle = new JLabel("Resultado Final");
        resultTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        resultTitle.setForeground(new Color(75, 61, 117));

        JTextArea resultArea = new JTextArea(
                "Pontua\u00e7\u00e3o: " + score + "/20\n"
                        + String.format("Aproveitamento: %.1f%%\n", percentage)
                        + "Nivel: " + nivel + " (" + englishLevel + ")\n\n"
                        + "Feedback final: " + feedbackFinal + "\n\n"
                        + saveStatus);

        resultArea.setEditable(false);
        resultArea.setFont(new Font("SansSerif", Font.PLAIN, 15));
        resultArea.setBackground(new Color(248, 245, 255));
        resultArea.setForeground(new Color(40, 34, 66));
        resultArea.setBorder(new EmptyBorder(10, 0, 0, 0));

        resultPanel.add(resultTitle, BorderLayout.NORTH);
        resultPanel.add(resultArea, BorderLayout.CENTER);

        Object[] options = {"Restart", "Close"};
        int choice = JOptionPane.showOptionDialog(
                frame,
                resultPanel,
                "Resultado da Entrevista",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == 0) {
            reiniciarEntrevista();
        } else {
            frame.dispose();
        }
    }

    private void reiniciarEntrevista() {
        score = 0;
        perguntaAtual = 0;
        candidateName = "";
        for (int i = 0; i < respostasUsuario.length; i++) {
            respostasUsuario[i] = "";
        }
        perguntaArea.setText(perguntas[0]);
        progressoLabel.setText("Question 1 of 5");
        barraProgresso.setValue(1);
        respostaArea.setText("");
        feedbackLabel.setText("Write your answer and click Next.");
        botaoProximo.setEnabled(true);
        cardLayout.show(mainPanel, "inicio");
    }

    private void showHistoryDialog() {
        ResultDAO dao = new ResultDAO();
        List<HistoryEntry> history = dao.listResults();

        if (dao.getLastErrorMessage() != null) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Nao foi possivel carregar o historico.\n"
                            + "Confira se a coluna data_tentativa existe na tabela resultados.\n\n"
                            + "Erro: " + dao.getLastErrorMessage(),
                    "Erro ao carregar historico",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] columns = {"Nome", "Pontuacao", "Nivel", "Data"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (HistoryEntry entry : history) {
            model.addRow(new Object[]{
                    entry.getName(),
                    entry.getScore(),
                    entry.getEnglishLevel(),
                    entry.getDate()
            });
        }

        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setForeground(new Color(45, 37, 78));
        table.setGridColor(new Color(230, 224, 247));
        table.setSelectionBackground(new Color(230, 223, 255));
        table.setSelectionForeground(new Color(45, 37, 78));
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(new Color(124, 92, 255));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 34));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(620, 220));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(214, 206, 242), 1));

        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        detailsArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        detailsArea.setForeground(new Color(48, 39, 86));
        detailsArea.setBackground(new Color(252, 250, 255));
        detailsArea.setBorder(new EmptyBorder(12, 12, 12, 12));

        if (!history.isEmpty()) {
            detailsArea.setText(formatHistoryDetails(history.get(0)));
            table.setRowSelectionInterval(0, 0);
        } else {
            detailsArea.setText("Nenhuma tentativa salva ainda.");
        }

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0 && selectedRow < history.size()) {
                    detailsArea.setText(formatHistoryDetails(history.get(selectedRow)));
                }
            }
        });

        JScrollPane detailsScrollPane = new JScrollPane(detailsArea);
        detailsScrollPane.setPreferredSize(new Dimension(620, 170));
        detailsScrollPane.setBorder(BorderFactory.createLineBorder(new Color(214, 206, 242), 1));

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(16, 16, 16, 16));
        panel.setBackground(new Color(246, 243, 255));

        JLabel title = new JLabel("Historico de Resultados");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(new Color(75, 61, 117));

        JLabel subtitle = new JLabel("Selecione uma tentativa para ver feedback e respostas.");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitle.setForeground(new Color(106, 94, 148));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);
        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(4));
        titlePanel.add(subtitle);

        JPanel tableCard = new JPanel(new BorderLayout());
        tableCard.setBackground(Color.WHITE);
        tableCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(214, 206, 242), 1),
                new EmptyBorder(10, 10, 10, 10)));
        tableCard.add(scrollPane, BorderLayout.CENTER);

        JPanel detailsCard = new JPanel(new BorderLayout(0, 8));
        detailsCard.setBackground(Color.WHITE);
        detailsCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(214, 206, 242), 1),
                new EmptyBorder(10, 10, 10, 10)));

        JLabel detailsTitle = new JLabel("Detalhes da Tentativa");
        detailsTitle.setFont(new Font("SansSerif", Font.BOLD, 15));
        detailsTitle.setForeground(new Color(75, 61, 117));

        detailsCard.add(detailsTitle, BorderLayout.NORTH);
        detailsCard.add(detailsScrollPane, BorderLayout.CENTER);

        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(tableCard, BorderLayout.CENTER);
        panel.add(detailsCard, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(
                frame,
                panel,
                "Historico",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private String extractCandidateName(String answer) {
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

    private String buildAnswersSummary() {
        StringBuilder summary = new StringBuilder();

        for (int i = 0; i < perguntas.length; i++) {
            String answer = respostasUsuario[i] == null || respostasUsuario[i].isBlank()
                    ? "(sem resposta)"
                    : respostasUsuario[i];
            summary.append("Pergunta ").append(i + 1).append(": ").append(perguntas[i]).append("\n");
            summary.append("Resposta: ").append(answer);

            if (i < perguntas.length - 1) {
                summary.append("\n\n");
            }
        }

        return summary.toString();
    }

    private String formatHistoryDetails(HistoryEntry entry) {
        String feedback = entry.getFeedback() == null || entry.getFeedback().isBlank()
                ? "Sem feedback salvo."
                : entry.getFeedback();
        String answers = entry.getAnswersSummary() == null || entry.getAnswersSummary().isBlank()
                ? "As respostas desta tentativa nao foram salvas."
                : entry.getAnswersSummary();

        return "Feedback final:\n" + feedback + "\n\n"
                + "Respostas da entrevista:\n" + answers;
    }

    private String capitalizeName(String text) {
        if (text.isBlank()) {
            return text;
        }

        String lower = text.toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }

    private String determineFinalEnglishLevel() {
        if (score <= 5) {
            return "A1";
        }
        if (score <= 10) {
            return "A2";
        }
        if (score <= 15) {
            return "B1";
        }
        return "B2";
    }

    private String determineFinalLabel(String englishLevel) {
        switch (englishLevel) {
            case "A1":
                return "Iniciante";
            case "A2":
                return "B\u00e1sico";
            case "B1":
                return "Bom";
            default:
                return "Excelente";
        }
    }

    private String determineFinalFeedback(String englishLevel) {
        switch (englishLevel) {
            case "A1":
                return "Voc\u00ea est\u00e1 come\u00e7ando. O importante \u00e9 tentar responder.";
            case "A2":
                return "Boa tentativa! Continue praticando frases simples em ingl\u00eas.";
            case "B1":
                return "Muito bom! Suas respostas foram claras e adequadas.";
            default:
                return "Excelente! Voc\u00ea respondeu muito bem para uma entrevista b\u00e1sica.";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfaceSimuladorStart::new);
    }
}
