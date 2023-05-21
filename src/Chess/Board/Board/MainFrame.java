package Chess.Board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public static GameloopController gameloop;

    public MainFrame() {
        setTitle("Schachspiel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);

        JLabel titleLabel = new JLabel("Menü");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        JButton newGameButton = new JButton("Neues Schachspiel");
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(newGameButton);
        mainPanel.add(Box.createVerticalStrut(10));

        JButton exitButton = new JButton("Applikation Beenden");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(exitButton);
        mainPanel.add(Box.createVerticalStrut(10));

        JButton achievementsButton = new JButton("Achievements");
        achievementsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(achievementsButton);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startChessGame();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        achievementsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAchievements();
            }
        });
    }

    private void startChessGame() {
        // Schachfeld neu initialisieren
        // Die folgenden 2 Zeilen sorgen dafür, dass die Konsole geleert wird
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Thread gameThread = new Thread(new Runnable() {
            public void run() {
                setVisible(false);
                gameloop = new GameloopController();
                gameloop.Startchess();

            }
        });

        gameThread.start();
    }

    private void showAchievements() {
        // Zeige Achievements-Fenster
        JOptionPane.showMessageDialog(this, "Hier sind alle Achievements:\n\n" +
                "1) Achievement XY [ ]\n" +
                "2) Achievement XY [ ]\n" +
                "3) Achievement XY [ ]");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    public static void restart() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }
}