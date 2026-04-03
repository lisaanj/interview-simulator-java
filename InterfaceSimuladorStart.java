import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InterfaceSimuladorStart {

    private int score = 0;
    private int perguntaAtual = 0;

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
                "This simulator helps students practice simple interview questions in English.\n\n" +
                "You will answer 5 questions.\n" +
                "After each answer, the system will show feedback and a suggestion.\n\n" +
                "Click the button below to begin."
        );
        descricao.setEditable(false);
        descricao.setFocusable(false);
        descricao.setFont(new Font("SansSerif", Font.PLAIN, 16));
        descricao.setForeground(new Color(55, 48, 90));
        descricao.setBackground(Color.WHITE);
        descricao.setLineWrap(true);
        descricao.setWrapStyleWord(true);
        descricao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 214, 245), 1),
                new EmptyBorder(20, 20, 20, 20)
        ));
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

        centro.add(Box.createVerticalGlue());
        centro.add(titulo);
        centro.add(Box.createVerticalStrut(10));
        centro.add(subtitulo);
        centro.add(Box.createVerticalStrut(25));
        centro.add(descricao);
        centro.add(Box.createVerticalStrut(25));
        centro.add(startButton);
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
                new EmptyBorder(22, 22, 22, 22)
        ));

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
                new EmptyBorder(12, 12, 12, 12)
        ));
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

        JButton botaoProximo = new JButton("Next");
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

        Result r = InterviewLogic.evaluateAnswer(tipos[perguntaAtual], resposta);
        score += r.score;

        feedbackLabel.setText(
                "<html><b>Feedback:</b> " + r.feedback +
                "<br><b>Suggestion:</b> " + r.suggestion + "</html>"
        );

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

        String nivel;
        String feedbackFinal;

        if (percentage < 30) {
            nivel = "Beginner";
            feedbackFinal = "You are just starting. Keep practicing simple answers.";
        } else if (percentage < 60) {
            nivel = "Basic";
            feedbackFinal = "Good effort! Practice more to improve your answers.";
        } else if (percentage < 85) {
            nivel = "Good";
            feedbackFinal = "Very good! Your answers were clear and appropriate.";
        } else {
            nivel = "Excellent";
            feedbackFinal = "Excellent! You answered very well in this interview simulation.";
        }

        JPanel resultPanel = new JPanel(new BorderLayout(10, 10));
        resultPanel.setBackground(new Color(248, 245, 255));
        resultPanel.setBorder(new EmptyBorder(18, 18, 18, 18));

        JLabel resultTitle = new JLabel("Final Result");
        resultTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        resultTitle.setForeground(new Color(75, 61, 117));

        JTextArea resultArea = new JTextArea(
                "Score: " + score + "/20\n" +
                String.format("Performance: %.1f%%\n", percentage) +
                "Level: " + nivel + "\n\n" +
                "Final feedback: " + feedbackFinal
        );

        resultArea.setEditable(false);
        resultArea.setFont(new Font("SansSerif", Font.PLAIN, 15));
        resultArea.setBackground(new Color(248, 245, 255));
        resultArea.setForeground(new Color(40, 34, 66));
        resultArea.setBorder(new EmptyBorder(10, 0, 0, 0));

        resultPanel.add(resultTitle, BorderLayout.NORTH);
        resultPanel.add(resultArea, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(
                frame,
                resultPanel,
                "Interview Result",
                JOptionPane.INFORMATION_MESSAGE
        );

        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfaceSimuladorStart::new);
    }
}