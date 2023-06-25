package Chess.Board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JTextField player1NameTextField;
    private JTextField player2NameTextField;
    private JButton player1ColorButton;
    private JButton player2ColorButton;
    private JButton lightsquareColorButton;
    private JButton darksquareColorButton;
    private JComboBox<String> chessPieceDesignComboBox;
    private JComboBox<String> prefixDesignComboBox;
    private Color dunkelgrau = new Color(43, 43, 43);
    private Color white = new Color(255,255,255);

    private String player1Name = "Player 10";
    private String prefixDesign = "";
    private String player2Name = "Player 2";
    private String chessPieceDesign = "Default";
    private Color lightsquareColor = new Color(240, 217, 181);
    private Color darksquareColor = new Color(181, 136, 99);
    public static GameloopController gameloop;
    int windowWidth = 1000;
    int windowHeight = 1000;

    static MainFrame instance;

    public MainFrame() {

        instance = this;

        setTitle("Schachspiel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setContentPane(new JLabel(new ImageIcon("Imgfiles/Background/ChessBG.png"))); // Passe den Pfad zum Bild an
        setLayout(new FlowLayout());

        initComponents();
        setSize(windowWidth, windowHeight); // Setze die Größe des Fensters
        setLocationRelativeTo(null);
    }


    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);

        mainPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Menü");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        JButton newGameButton = new JButton(" Neues Schachspiel  ");
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(newGameButton);
        mainPanel.add(Box.createVerticalStrut(10));

        JButton designButton = new JButton(" Design auswaehlen ");
        designButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(designButton);
        mainPanel.add(Box.createVerticalStrut(10));

        JButton achievementsButton = new JButton("      Achievements      "); //damit die buttons alle die selbe größe haben spart code
        achievementsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(achievementsButton);
        mainPanel.add(Box.createVerticalStrut(10));

        JButton lexikonButton = new JButton("            Lexikon            "); //damit die buttons alle die selbe größe haben spart code
        lexikonButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lexikonButton);
        mainPanel.add(Box.createVerticalStrut(10));

        JButton exitButton = new JButton("Applikation Beenden");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(exitButton);

        newGameButton.addActionListener(e -> startChessGame());

        designButton.addActionListener(e -> design());

        exitButton.addActionListener(e -> System.exit(0));

        achievementsButton.addActionListener(e -> showAchievements());

        lexikonButton.addActionListener(e -> showLexikon());

    }

    private void startChessGame() {
        // Schachfeld neu initialisieren
        // Die folgenden 2 Zeilen sorgen dafür, dass die Konsole geleert wird
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Thread gameThread = new Thread(() -> {
            setVisible(false);
            gameloop = new GameloopController();
            gameloop.Startchess();

        });

        gameThread.start();
    }
    private void showLexikon() {
        JFrame innerFrame = new JFrame("Schach-Lexikon");
        innerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        innerFrame.setSize(1000, 1000);



        JPanel cardPanel = new JPanel(new CardLayout());



        // Seite 1: Schritte für Schach
        String schritteText = """
                Vorbereitung

                Schritt 1: Aufbau des Schachbretts
                Am Anfang des Spiels wird das Schachbrett so aufgebaut, dass jeder Spieler das weiße (oder hellere) Feld unten rechts hat.
                Die Schachfiguren werden jedes Mal auf die gleiche Weise angeordnet. Die zweite Reihe wird mit Bauern gefüllt. Die Türme kommen in die Ecken,
                dann die Springer neben ihnen, gefolgt von den Läufern und schließlich die Dame,
                die immer auf ihr entsprechendes Farbfeld gestellt wird (weiße Dame auf weiß, schwarze Dame auf schwarz), und der König auf das verbleibende Feld.

                Schritt 2: Die Züge der Figuren
                Jede Schachfigur hat ihre eigenen Bewegungsregeln. Der Bauer zieht ein Feld nach vorne, kann jedoch beim ersten Zug auch zwei Felder vorrücken.
                Er schlägt diagonal und kann bei der Umwandlung zur Dame, falls er die gegnerische Grundreihe erreicht, jede Figur wählen. Die Türme können horizontal und vertikal ziehen,
                solange kein Hindernis im Weg ist. Die Springer ziehen in einem L-förmigen Muster, zwei Felder geradeaus und dann ein Feld zur Seite.
                Die Läufer ziehen diagonal in alle Richtungen. Die Dame kann in alle Richtungen ziehen, horizontal, vertikal und diagonal.
                Der König kann ein Feld in jede Richtung ziehen, aber nicht auf ein Feld, das von einer gegnerischen Figur angegriffen wird.
                Es gibt auch spezielle Züge wie die Rochade, bei der der König mit einem Turm tauscht, und die en passant Regel, bei der ein Bauer einen anderen Bauer schlägt.

                Schritt 3: Ziel des Spiels
                Das Ziel des Schachspiels besteht darin, den gegnerischen König in eine Position zu bringen, aus der er nicht mehr entkommen kann,
                das heißt, ihn im Schachmatt zu setzen. Dies geschieht, wenn der König im Schach steht und keine legale Zugmöglichkeit mehr hat, um dem Angriff zu entkommen.
                Der Spieler, der den gegnerischen König schachmatt setzt, gewinnt die Partie.

                Schritt 4: Sonderregeln
                Neben den grundlegenden Zügen gibt es noch einige Sonderregeln im Schach. Dazu gehören die Rochade, bei der der König und ein Turm gleichzeitig gezogen werden,
                um den König in eine sicherere Position zu bringen. Die en passant Regel erlaubt es einem Bauern, einen gegnerischen Bauern zu schlagen,
                der gerade zwei Felder vorgerückt ist. Des Weiteren gibt es noch die Umwandlung, bei der ein Bauer die gegnerische Grundreihe erreicht und in eine andere Figur umgewandelt werden kann.
                Schließlich gibt es noch das Remis, bei dem das Spiel unentschieden endet, zum Beispiel bei Patt (der König steht nicht im Schach, aber der Spieler hat keinen legalen Zug).

                """;

        JTextArea schritteTextArea = new JTextArea(schritteText);
        schritteTextArea.setEditable(false);
        schritteTextArea.setLineWrap(true);
        schritteTextArea.setWrapStyleWord(true);
        schritteTextArea.setBackground(new Color(255, 255, 0, 128)); // Hintergrundfarbe festlegen
        JScrollPane schritteScrollPane = new JScrollPane(schritteTextArea);
        schritteScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cardPanel.add(schritteScrollPane);

        // Seite 2: Regeln des Schachs
        String regelnText = """
                Regeln des Schachs:
                Schach ist ein strategisches Brettspiel zwischen zwei Spielern, bei dem das Ziel darin besteht, den gegnerischen König zu schlagen.
                Die Spieler ziehen abwechselnd und dürfen jeweils eine ihrer Figuren bewegen. Eine Figur kann auf ein freies Feld ziehen oder eine gegnerische Figur schlagen.
                Der König darf nicht ins Schach gezogen werden, das heißt, er darf nicht auf ein Feld ziehen, das von einer gegnerischen Figur angegriffen wird.
                Wenn ein Spieler seinen König nicht vor einem Schachgebot schützen kann, ist er im Schach.
                Das Spiel kann auf verschiedene Arten enden: durch Schachmatt, Remis (Unentschieden) oder Aufgabe eines Spielers.

                Schachmatt: Ein Spieler setzt den gegnerischen König schachmatt, sodass dieser keinen legalen Zug mehr machen kann.
                Remis: Es gibt verschiedene Möglichkeiten für ein Remis, z.B. durch dreifache Wiederholung der Zugfolge, unzureichendes Material oder durch Stellungswiederholung.

                """;

        JTextArea regelnTextArea = new JTextArea(regelnText);
        regelnTextArea.setEditable(false);
        regelnTextArea.setLineWrap(true);
        regelnTextArea.setWrapStyleWord(true);
        regelnTextArea.setBackground(new Color(255, 255, 0, 128)); // Hintergrundfarbe festlegen
        JScrollPane regelnScrollPane = new JScrollPane(regelnTextArea);
        regelnScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cardPanel.add(regelnScrollPane);

        // Seite 3: Beschreibung der Figuren
        String figurenText = """
                Schachfiguren:

                1. König: Der König ist die wichtigste Figur im Schach. Er kann ein Feld in jede Richtung ziehen.
                   Der König darf jedoch nicht auf ein Feld ziehen, das von einer gegnerischen Figur bedroht wird.

                2. Dame: Die Dame ist die mächtigste Figur im Schach. Sie kann sich horizontal, vertikal und diagonal bewegen.
                   Sie darf so viele Felder ziehen, wie sie möchte, solange keine Hindernisse im Weg sind.

                3. Türme: Die Türme können sich horizontal und vertikal bewegen. Sie dürfen so viele Felder ziehen,
                   wie sie möchten, solange keine Hindernisse im Weg sind.

                4. Läufer: Die Läufer bewegen sich diagonal. Sie dürfen so viele Felder ziehen, wie sie möchten,
                   solange keine Hindernisse im Weg sind.

                5. Springer: Die Springer bewegen sich in einem L-förmigen Muster. Sie können zwei Felder geradeaus ziehen
                   und dann ein Feld zur Seite oder ein Feld zur Seite und dann zwei Felder geradeaus.

                6. Bauern: Die Bauern sind die schwächsten Figuren im Schach. Sie ziehen ein Feld nach vorne,
                   können aber beim ersten Zug auch zwei Felder vorrücken. Sie schlagen diagonal und können bei Erreichen
                   der gegnerischen Grundreihe in eine andere Figur umgewandelt werden.

                """;

        JTextArea figurenTextArea = new JTextArea(figurenText);
        figurenTextArea.setEditable(false);
        figurenTextArea.setLineWrap(true);
        figurenTextArea.setWrapStyleWord(true);
        figurenTextArea.setBackground(new Color(255, 255, 0, 128)); // Hintergrundfarbe festlegen
        JScrollPane figurenScrollPane = new JScrollPane(figurenTextArea);
        figurenScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cardPanel.add(figurenScrollPane);

        // Seite 4: Tipps und Tricks
        String tippsText = """
                Tipps und Tricks:

                1. Entwickle zu Beginn des Spiels deine Figuren, indem du sie vom Startfeld bewegst und Raum für
                   andere Figuren schaffst. Achte jedoch darauf, nicht zu viele Bauern zu bewegen, da dies zu
                   Schwächungen in der Königsstellung führen kann.

                2. Halte deine Königsstellung sicher, indem du ihn frühzeitig rochierst und deine Türme auf die
                   offenen Linien entwickelst. Dies gibt deinem König Schutz und erlaubt dir, deine Türme zu
                   verbinden, um Angriffe auf den Gegner zu starten.

                3. Kontrolliere das Zentrum des Schachbretts, da dies dir mehr Möglichkeiten für Angriffe und
                   Verteidigungen bietet. Platziere deine Figuren strategisch, um das Zentrum zu beherrschen und
                   den Gegner unter Druck zu setzen.

                4. Führe keine unnötigen Figurenopfer durch, es sei denn, du hast einen klaren Vorteil davon.
                   Bedenke die Wertigkeit der Figuren und versuche, Materialvorteile zu erlangen, indem du die
                   Figuren deines Gegners effektiv angreifst und eroberst.

                5. Analysiere die Stellung gründlich und plane deine Züge im Voraus. Achte auf mögliche
                   taktische Motive wie Gabeln, Spieße, Fesselungen und andere taktische Möglichkeiten, um
                   deinen Gegner zu überraschen und Vorteile zu erlangen.

                """;

        JTextArea tippsTextArea = new JTextArea(tippsText);
        tippsTextArea.setEditable(false);
        tippsTextArea.setLineWrap(true);
        tippsTextArea.setWrapStyleWord(true);
        tippsTextArea.setBackground(new Color(255, 255, 0, 128)); // Hintergrundfarbe festlegen
        JScrollPane tippsScrollPane = new JScrollPane(tippsTextArea);
        tippsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cardPanel.add(tippsScrollPane);

        // Füge die Karten zum Kartenpanel hinzu
        innerFrame.getContentPane().add(cardPanel, BorderLayout.CENTER);

        // Erstelle Buttons für die Seiten-Navigation
        JPanel buttonPanel = new JPanel();

        JButton previousButton = new JButton("Vorherige Seite");
        previousButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.previous(cardPanel);
        });
        buttonPanel.add(previousButton);

        JButton nextButton = new JButton("Nächste Seite");
        nextButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.next(cardPanel);
        });
        buttonPanel.add(nextButton);

        // Füge das Buttonpanel zur unteren Seite des Frames hinzu
        innerFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Zeige den Frame an
        innerFrame.setVisible(true);
        innerFrame.setLocationRelativeTo(null); // Zentriert den Frame auf dem Bildschirm
    }


    private void showAchievements() {
        // Zeige Achievements-Fenster
        JOptionPane.showMessageDialog(this, "Hier sind alle Achievements:\n\n" +
                "1) Bong-Cloud\n" +
                "2) Botez-Gambit\n" +
                "3) Oh-no-my-queen\n" +
                "4) Bauer-findet-Frau\n" +
                "5) Pawn-to-Win\n" +
                "6) Too-Easy\n" +
                "7) Full-House\n" +
                "8) How-long-does-it-take?\n" +
                "9) Not-even-close\n" +
                "10) Better-King-wins!");
    }


    public void design() {

        JFrame innerFrame = new JFrame("Design");
        innerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        innerFrame.setSize(500, 500);
        innerFrame.setLayout(new BorderLayout());
        innerFrame.getContentPane().setBackground(dunkelgrau);

        JPanel cardPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        cardPanel.setBackground(dunkelgrau);

        JLabel titleLabel = new JLabel("Brett und Figuren");
        titleLabel.setForeground(white);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        cardPanel.add(titleLabel, gbc);

        JLabel player1NameLabel = new JLabel("Player 1 Name:");
        player1NameLabel.setForeground(white);
        player1NameTextField = new JTextField();
        player1NameTextField.setPreferredSize(new Dimension(200, 30));

        JLabel player2NameLabel = new JLabel("Player 2 Name:");
        player2NameLabel.setForeground(white);
        player2NameTextField = new JTextField();
        player2NameTextField.setPreferredSize(new Dimension(200, 30));

        JLabel chessPieceDesignLabel = new JLabel("Chess Piece Designs:");
        chessPieceDesignLabel.setForeground(white);
        String[] chessPieceDesigns = {"Default", "Wood", "Old", "Modern"};
        chessPieceDesignComboBox = new JComboBox<>(chessPieceDesigns);
        chessPieceDesignComboBox.setPreferredSize(new Dimension(200, 30));

        JLabel prefixDesignLabel = new JLabel("Prefixes:");
        prefixDesignLabel.setForeground(white);
        String[] prefixDesign = {"","Default Prefix", "Wood Prefix", "Old Prefix", "Modern Prefix"};
        prefixDesignComboBox = new JComboBox<>(prefixDesign);
        prefixDesignComboBox.setPreferredSize(new Dimension(200, 30));

        JLabel lightsquareLabel = new JLabel("Lightsquare:");
        lightsquareLabel.setForeground(white);
        lightsquareColorButton = new JButton("Choose");
        lightsquareColorButton.setBackground(lightsquareColor);
        lightsquareColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseColor(innerFrame, "Lightsquare Color", lightsquareColorButton);
            }
        });

        JLabel darksquareLabel = new JLabel("Darksquare:");
        darksquareLabel.setForeground(white);
        darksquareColorButton = new JButton("Choose");
        darksquareColorButton.setBackground(darksquareColor);
        darksquareColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseColor(innerFrame, "Darksquare Color", darksquareColorButton);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        cardPanel.add(player1NameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        cardPanel.add(player1NameTextField, gbc);

        player1ColorButton = new JButton("Choose");
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        cardPanel.add(player1ColorButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        cardPanel.add(player2NameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        cardPanel.add(player2NameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        cardPanel.add(chessPieceDesignLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        cardPanel.add(chessPieceDesignComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_END;
        cardPanel.add(prefixDesignLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        cardPanel.add(prefixDesignComboBox, gbc);

        player2ColorButton = new JButton("Choose");
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        cardPanel.add(player2ColorButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_END;
        cardPanel.add(lightsquareLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        cardPanel.add(lightsquareColorButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.LINE_END;
        cardPanel.add(darksquareLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        cardPanel.add(darksquareColorButton, gbc);

        innerFrame.add(cardPanel, BorderLayout.NORTH);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSettings();
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetSettings();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(dunkelgrau);
        buttonPanel.add(saveButton);
        buttonPanel.add(resetButton);

        innerFrame.add(buttonPanel, BorderLayout.SOUTH);

        innerFrame.setVisible(true);
    }

    private void chooseColor(JFrame frame, String title, JButton colorButton) {
        Color initialColor = JColorChooser.showDialog(frame, title, colorButton.getBackground());
        if (initialColor != null) {
            colorButton.setBackground(initialColor);
        }
    }

    private void saveSettings() {
         player1Name = player1NameTextField.getText();
         player2Name = player2NameTextField.getText();
         chessPieceDesign = (String) chessPieceDesignComboBox.getSelectedItem();
         lightsquareColor = lightsquareColorButton.getBackground();
         darksquareColor = darksquareColorButton.getBackground();
         prefixDesign = (String) chessPieceDesignComboBox.getSelectedItem();

        // Print the obtained settings for demonstration
        System.out.println("Player 1 Name: " + player1Name);
        System.out.println("Player 2 Name: " + player2Name);
        System.out.println("Chess Piece Design: " + chessPieceDesign);
        System.out.println("Lightsquare Color: " + lightsquareColor);
        System.out.println("Darksquare Color: " + darksquareColor);
    }


    private void resetSettings() {
        player1NameTextField.setText("Player 1");
        player2NameTextField.setText("Player 2");
        chessPieceDesignComboBox.setSelectedItem("Default");
        prefixDesignComboBox.setSelectedItem("");
        lightsquareColorButton.setBackground(new Color(240, 217, 181));
        darksquareColorButton.setBackground(new Color(181, 136, 99));
    }

    public String getPlayer1Name(){
        return player1Name;
    }
    public String getPlayer2Name(){
        return player2Name;
    }
    public String getChessPieceDesign(){
        return chessPieceDesign;
    }
    public String getPrefix(){
        return prefixDesign;
    }

    public Color getLightsquareColor(){
        return lightsquareColor;
    }

    public Color getDarksquareColor(){
        return darksquareColor;
    }


    public static MainFrame getInstance() {

        return instance;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }

    public static void restart() {
        SwingUtilities.invokeLater(() -> {
            gameloop.resetBoard();
            gameloop.resetUI();
            new MainFrame().setVisible(true);

        });
    }
}