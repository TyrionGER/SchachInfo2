package Chess.Board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private JTextField player1NameTextField;
    private JTextField player2NameTextField;
    private JButton player1ColorButton;
    private JButton player2ColorButton;
    private JButton lightsquareColorButton;
    private JButton canGoColorButton;
    private JButton darksquareColorButton;
    private JButton whatPieceColorButton;
    private boolean achievement = false;
    private JComboBox<String> chessPieceDesignComboBox;
    private Color dunkelgrau = new Color(43, 43, 43);
    private Color white = new Color(255,255,255);

    private Color canGoColor = new Color(51,204,0);
    private ImageIcon image1, image2, image3, image4, image5, image6;
    private String player1Avatar, player2Avatar;
    private String player1Name = "Player 1";
    private String player2Name = "Player 2";
    private String chessPieceDesign = "Default";
    private Color lightsquareColor = new Color(240, 217, 181);
    private Color darksquareColor = new Color(181, 136, 99);
    private Color whatPieceColor = new Color(247, 247, 105);
    public static GameloopController gameloop;
    int windowWidth = 1000;
    int windowHeight = 1000;
    private static boolean achievement1 = false, achievement2 = false, achievement3 = false, achievement4= false, achievement5 = false, achievement6 = false, achievement7 = false;

    static MainFrame instance;

    public MainFrame() {

        instance = this;

        setTitle("Schachspiel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setContentPane(new JLabel(new ImageIcon("Imgfiles/Background/ChessBG.png")));
        setLayout(new FlowLayout());

        initComponents();
        setSize(windowWidth, windowHeight);
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

        JButton avatarButton = new JButton("              Avatar             ");
        avatarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(avatarButton);
        mainPanel.add(Box.createVerticalStrut(10));

        JButton achievementsButton = new JButton("      Achievements      ");
        achievementsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(achievementsButton);
        mainPanel.add(Box.createVerticalStrut(10));

        JButton lexikonButton = new JButton("            Lexikon            ");
        lexikonButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lexikonButton);
        mainPanel.add(Box.createVerticalStrut(10));

        JButton exitButton = new JButton("Applikation Beenden");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(exitButton);

        newGameButton.addActionListener(e -> startChessGame());

        designButton.addActionListener(e -> design());
        avatarButton.addActionListener(e -> createAvatar());

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
        String basisRegelnText = """
                
                Basis-Regeln:
                
                Das Schachbrett:
                Das Schachbrett besteht aus 64 Feldern, abwechselnd hellen (weißen) und dunklen (schwarzen) Feldern. 
                Es hat 8 Reihen (waagerecht) und 8 Linien (senkrecht). Die Spieler sitzen einander gegenüber, wobei das rechte Eckfeld des Schachbretts für jeden Spieler ein weißes Feld ist. 
                                                                                                                                                           
                Die Schachfiguren: 
                Jeder Spieler hat 16 Schachfiguren, die zu Beginn des Spiels in einer bestimmten Formation auf dem Schachbrett platziert sind. 
                Die Figuren bestehen aus dem König, der Dame, zwei Türmen, zwei Springern, zwei Läufern und acht Bauern.
                                                                                                                                                                                            
                Das Ziel des Spiels: 
                Das Ziel des Schachspiels ist es, den gegnerischen König in eine Position zu bringen, in der er im nächsten Zug bedroht (Schach) ist und keine legale Zugmöglichkeit hat, 
                um dem Schach zu entkommen. Dieser Zustand wird "Schachmatt" genannt, und der Spieler, dessen König schachmatt gesetzt wurde, verliert das Spiel.
                                                                                                                                                                                            
                Die Zugregeln: 
                Jede Schachfigur hat spezifische Bewegungsmöglichkeiten. Der König kann ein Feld in jede Richtung bewegen.
                Die Dame kann sich horizontal, vertikal und diagonal über das gesamte Schachbrett bewegen. Die Türme bewegen sich horizontal und vertikal.
                Die Läufer bewegen sich diagonal. Die Springer bewegen sich in einem "L"-förmigen Sprungmuster. Die Bauern bewegen sich vorwärts, aber sie schlagen diagonal.
                                                                                                                                                                                            
                Schlagen: 
                Wenn eine Figur auf ein Feld zieht, das von einer gegnerischen Figur besetzt ist, wird die gegnerische Figur geschlagen und aus dem Spiel genommen.
                                                                                                                                                                                            
                Rochade: 
                Die Rochade ist ein spezieller Zug, bei dem der König und ein Turm gleichzeitig bewegt werden. Sie ermöglicht es dem König, 
                sich in eine sicherere Position zu begeben und den Turm zu entwickeln.
                                                                                                                                                                                           
                En Passant: 
                Dies ist ein spezieller Zug, der beim Vorbeiziehen eines Bauern ermöglicht wird, der zwei Felder von seinem Startfeld aus vorrückt und einen gegnerischen Bauern passiert.
                                                                                                                                                                                            
                Patt: 
                Wenn ein Spieler keinen legalen Zug mehr machen kann und sein König nicht im Schach steht, endet das Spiel in einem Patt. In diesem Fall endet das Spiel unentschieden.

                """;

        JTextArea basisRegelnTextArea = new JTextArea(basisRegelnText);
        basisRegelnTextArea.setEditable(false);
        basisRegelnTextArea.setLineWrap(true);
        basisRegelnTextArea.setWrapStyleWord(true);
        basisRegelnTextArea.setBackground(new Color(43, 43, 43)); // Hintergrundfarbe festlegen
        basisRegelnTextArea.setForeground(Color.white);
        JScrollPane schritteScrollPane = new JScrollPane(basisRegelnTextArea);
        schritteScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cardPanel.add(schritteScrollPane);

        // Seite 2: Beschreibung der Figuren
        String figurenText = """
                Schachfiguren:

                1. König: 
                   Der König ist die wichtigste Figur im Schach. Er kann ein Feld in jede Richtung ziehen.
                   Der König darf jedoch nicht auf ein Feld ziehen, das von einer gegnerischen Figur bedroht wird.

                2. Dame: 
                   Die Dame ist die mächtigste Figur im Schach. Sie kann sich horizontal, vertikal und diagonal bewegen.
                   Sie darf so viele Felder ziehen, wie sie möchte, solange keine Hindernisse im Weg sind.

                3. Türme: 
                   Die Türme können sich horizontal und vertikal bewegen. Sie dürfen so viele Felder ziehen,
                   wie sie möchten, solange keine Hindernisse im Weg sind.

                4. Läufer: 
                   Die Läufer bewegen sich diagonal. Sie dürfen so viele Felder ziehen, wie sie möchten,
                   solange keine Hindernisse im Weg sind.

                5. Springer: 
                   Die Springer bewegen sich in einem L-förmigen Muster. Sie können zwei Felder geradeaus ziehen
                   und dann ein Feld zur Seite oder ein Feld zur Seite und dann zwei Felder geradeaus.

                6. Bauern:  
                   Die Bauern sind die schwächsten Figuren im Schach. Sie ziehen ein Feld nach vorne,
                   können aber beim ersten Zug auch zwei Felder vorrücken. Sie schlagen diagonal und können bei Erreichen
                   der gegnerischen Grundreihe in eine andere Figur umgewandelt werden.

                """;

        JTextArea figurenTextArea = new JTextArea(figurenText);
        figurenTextArea.setEditable(false);
        figurenTextArea.setLineWrap(true);
        figurenTextArea.setWrapStyleWord(true);
        figurenTextArea.setBackground(new Color(43, 43, 43)); // Hintergrundfarbe festlegen
        figurenTextArea.setForeground(Color.white);
        JScrollPane figurenScrollPane = new JScrollPane(figurenTextArea);
        figurenScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cardPanel.add(figurenScrollPane);

        // Seite 3: Tipps und Tricks
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
        tippsTextArea.setBackground(new Color(43, 43, 43)); // Hintergrundfarbe festlegen
        tippsTextArea.setForeground(Color.white);
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

    private void createAvatar() {
        // Erstelle das Hauptfenster
        JFrame avatarFrame = new JFrame("Avatar erstellen");
        avatarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        avatarFrame.setSize(400, 200);
        avatarFrame.setLayout(new FlowLayout());
        avatarFrame.getContentPane().setBackground(dunkelgrau);


        JButton selectButton = new JButton("Spieler 1");
        JButton selectButton2 = new JButton("Spieler 2");


        avatarFrame.add(selectButton);
        avatarFrame.add(selectButton2);

        // Handler für den "Avatar auswählen"-Button
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Erstelle das Fenster für die vorgeschlagenen Avatare
                JFrame selectFrame = new JFrame("Vorgeschlagene Avatare");
                selectFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                selectFrame.setSize(400, 200);
                selectFrame.setLayout(new FlowLayout());
                selectFrame.getContentPane().setBackground(dunkelgrau);

                JLabel imageLabel1 = new JLabel();
                JLabel imageLabel2 = new JLabel();
                JLabel imageLabel3 = new JLabel();
                JLabel imageLabel4 = new JLabel();
                JLabel imageLabel5 = new JLabel();
                JLabel imageLabel6 = new JLabel();

                image1 = new ImageIcon("Imgfiles/Avatare/AngryMonkey.JPG");
                image2 = new ImageIcon("Imgfiles/Avatare/Dude.JPG");
                image3 = new ImageIcon("Imgfiles/Avatare/Frau.JPG");
                image4 = new ImageIcon("Imgfiles/Avatare/Ghost.JPG");
                image5 = new ImageIcon("Imgfiles/Avatare/Katze.JPG");
                image6 = new ImageIcon("Imgfiles/Avatare/smoke.JPG");

                imageLabel1.setIcon(image1);
                imageLabel2.setIcon(image2);
                imageLabel3.setIcon(image3);
                imageLabel4.setIcon(image4);
                imageLabel5.setIcon(image5);
                imageLabel6.setIcon(image6);

                // Füge den MouseListener zu jedem JLabel hinzu
                imageLabel1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        player1Avatar = "Imgfiles/Avatare/AngryMonkey.JPG";
                        System.out.println("Avatar 1 ausgewählt");


                    }
                });

                imageLabel2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        player1Avatar = "Imgfiles/Avatare/Dude.JPG";
                        System.out.println("Avatar 2 ausgewählt");


                    }
                });

                imageLabel3.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        player1Avatar = "Imgfiles/Avatare/Frau.JPG";
                        System.out.println("Avatar 3 ausgewählt");

                    }
                });

                imageLabel4.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        player1Avatar = "Imgfiles/Avatare/Ghost.JPG";
                        System.out.println("Avatar 4 ausgewählt");
                    }
                });

                imageLabel5.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        player1Avatar = "Imgfiles/Avatare/Katze_Fisch.JPG";
                        System.out.println("Avatar 5 ausgewählt");
                    }
                });

                imageLabel6.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        player1Avatar = "Imgfiles/Avatare/smoke.JPG";
                        System.out.println("Avatar 6 ausgewählt");
                    }
                });



                selectFrame.add(imageLabel1);
                selectFrame.add(imageLabel2);
                selectFrame.add(imageLabel3);
                selectFrame.add(imageLabel4);
                selectFrame.add(imageLabel5);
                selectFrame.add(imageLabel6);

                selectFrame.pack();

                selectFrame.setVisible(true);
                selectFrame.setLocationRelativeTo(null);

            }
        });

        selectButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Erstelle das Fenster für die vorgeschlagenen Avatare
                JFrame selectFrame = new JFrame("Vorgeschlagene Avatare");
                selectFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                selectFrame.setSize(400, 200);
                selectFrame.setLayout(new FlowLayout());
                selectFrame.getContentPane().setBackground(dunkelgrau);

                JLabel imageLabel1 = new JLabel();
                JLabel imageLabel2 = new JLabel();
                JLabel imageLabel3 = new JLabel();
                JLabel imageLabel4 = new JLabel();
                JLabel imageLabel5 = new JLabel();
                JLabel imageLabel6 = new JLabel();

                image1 = new ImageIcon("Imgfiles/Avatare/AngryMonkey.JPG");
                image2 = new ImageIcon("Imgfiles/Avatare/Dude.JPG");
                image3 = new ImageIcon("Imgfiles/Avatare/Frau.JPG");
                image4 = new ImageIcon("Imgfiles/Avatare/Ghost.JPG");
                image5 = new ImageIcon("Imgfiles/Avatare/Katze.JPG");
                image6 = new ImageIcon("Imgfiles/Avatare/smoke.JPG");

                imageLabel1.setIcon(image1);
                imageLabel2.setIcon(image2);
                imageLabel3.setIcon(image3);
                imageLabel4.setIcon(image4);
                imageLabel5.setIcon(image5);
                imageLabel6.setIcon(image6);

                // Füge den MouseListener zu jedem JLabel hinzu
                imageLabel1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        player2Avatar = "Imgfiles/Avatare/AngryMonkey.JPG";
                        System.out.println("Avatar 1 ausgewählt");

                    }
                });

                imageLabel2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        player2Avatar = "Imgfiles/Avatare/Dude.JPG";
                        System.out.println("Avatar 2 ausgewählt");

                    }
                });

                imageLabel3.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        player2Avatar = "Imgfiles/Avatare/Frau.JPG";
                        System.out.println("Avatar 3 ausgewählt");
                    }
                });

                imageLabel4.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        player2Avatar = "Imgfiles/Avatare/Ghost.JPG";
                        System.out.println("Avatar 4 ausgewählt");
                    }
                });

                imageLabel5.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        player2Avatar = "Imgfiles/Avatare/Katze_Fisch.JPG";
                        System.out.println("Avatar 5 ausgewählt");
                    }
                });

                imageLabel6.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        player2Avatar = "Imgfiles/Avatare/smoke.JPG";
                        System.out.println("Avatar 6 ausgewählt");
                    }
                });



                selectFrame.add(imageLabel1);
                selectFrame.add(imageLabel2);
                selectFrame.add(imageLabel3);
                selectFrame.add(imageLabel4);
                selectFrame.add(imageLabel5);
                selectFrame.add(imageLabel6);

                selectFrame.pack();

                selectFrame.setVisible(true);
                selectFrame.setLocationRelativeTo(null);

            }
        });

        // Zeige das Hauptfenster an
        avatarFrame.setVisible(true);
        avatarFrame.setLocationRelativeTo(null);

    }


    private void showAchievements() {
        // Zeige Achievements-Fenster
        JOptionPane.showMessageDialog(this, ("Hier sind alle Achievements:\n\n" +
                "1) Bong-Cloud " + (achievement1 == true ? "Freigeschalten" : "Gesperrt") + "\n" + //Lennart
                "2) Pawn-to-Win " + (achievement2 == true ? "Freigeschalten" : "Gesperrt") + "\n" +//Samir
                "3) Bauer-findet-Frau " + (achievement3 == true ? "Freigeschalten" : "Gesperrt") + "\n" +//Samir
                "4) Full-House " + (achievement4 == true ? "Freigeschalten" : "Gesperrt") + "\n" +//Olawale
                "5) How-long-does-it-take? " + (achievement5 == true ? "Freigeschalten" : "Gesperrt") + "\n" +//Olawale
                "6) Cocky-much?? " + (achievement6 == true ? "Freigeschalten" : "Gesperrt") + "\n" +//Lennart
                "7) The-French-Move ") + (achievement7 == true ? "Freigeschalten" : "Gesperrt") + "\n" ) ;//Lennart
    }
    public void setAchievement(int index) {
        switch(index){
            case 0:
                return;
            case 1:
                achievement1 = true;
                JOptionPane.showMessageDialog(null, "Achievement Bong-Cloud freigeschalten!");
                return;
            case 2:
                //achievement1 = true;
                JOptionPane.showMessageDialog(null, "Achievement Botez-Gambit freigeschalten!");
                return;
            case 3:
                //achievement1 = true;
                JOptionPane.showMessageDialog(null, "Achievement Oh-no-my-Queen freigeschalten!");
                return;
            case 4:
                achievement3 = true;
                JOptionPane.showMessageDialog(null, "Achievement Bauer-findet-Frau freigeschalten!");
                return;
            case 5:
                achievement2 = true;
                JOptionPane.showMessageDialog(null, "Achievement Pawn-to-Win freigeschalten!");
                return;
            case 6:
                achievement6 = true;
                JOptionPane.showMessageDialog(null, "Achievement Cocky-Much?? freigeschalten!");
                return;
            case 7:
                achievement4 = true;
                JOptionPane.showMessageDialog(null, "Achievement Full-House freigeschalten!");
                return;
            case 8:
                achievement5 = true;
                JOptionPane.showMessageDialog(null, "Achievement How-long-does-it-take? freigeschalten!");
                return;
            case 9:
                achievement7 = true;
                JOptionPane.showMessageDialog(null, "Achievement The-French-Move freigeschalten!");
                return;
            case 10:
                //achievement1 = true;
                JOptionPane.showMessageDialog(null, "Achievement Better-King-wins! freigeschalten!");
                return;
        }
        JOptionPane.showMessageDialog(null, "Achievement freigeschalten!");
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

        JLabel chessPieceDesignLabel = new JLabel("Designs und Prefixes [= Überschreibt alles]:");
        chessPieceDesignLabel.setForeground(white);
        String[] chessPieceDesigns = {"Default", "Default Prefix", "Old", "Old Prefix", "Modern", "Modern Prefix"};
        chessPieceDesignComboBox = new JComboBox<>(chessPieceDesigns);
        chessPieceDesignComboBox.setPreferredSize(new Dimension(200, 30));

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

        JLabel canGoColorLabel = new JLabel("Moegliche Zuege Farbe:");
        canGoColorLabel.setForeground(white);
        canGoColorButton = new JButton("Choose");
        canGoColorButton.setBackground(canGoColor);
        canGoColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseColor(innerFrame, "CanGoColor", canGoColorButton);
            }
        });

        JLabel whatPieceColorLabel = new JLabel("Welche Figur Farbe:");
        whatPieceColorLabel.setForeground(white);
        whatPieceColorButton = new JButton("Choose");
        whatPieceColorButton.setBackground(whatPieceColor);
        whatPieceColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseColor(innerFrame, "What piece Color", whatPieceColorButton);
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


        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.LINE_END;
        cardPanel.add(canGoColorLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        cardPanel.add(canGoColorButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.LINE_END;
        cardPanel.add(whatPieceColorLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        cardPanel.add(whatPieceColorButton, gbc);

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
         canGoColor = canGoColorButton.getBackground();
         whatPieceColor = whatPieceColorButton.getBackground();

        // Print the obtained settings for demonstration
        System.out.println("Player 1 Name: " + player1Name);
        System.out.println("Player 2 Name: " + player2Name);
        System.out.println("Chess Piece Design: " + chessPieceDesign);
        System.out.println("Lightsquare Color: " + lightsquareColor);
        System.out.println("Darksquare Color: " + darksquareColor);
        System.out.println("canGoColor Color: " + canGoColor);
        System.out.println("what Piece Color: " + getWhatPieceColor());
    }


    private void resetSettings() {
        player1NameTextField.setText("Player 1");
        player2NameTextField.setText("Player 2");
        chessPieceDesignComboBox.setSelectedItem("Default");
        lightsquareColorButton.setBackground(new Color(240, 217, 181));
        darksquareColorButton.setBackground(new Color(181, 136, 99));
        canGoColorButton.setBackground(new Color(51,204,0));
        whatPieceColorButton.setBackground(new Color(247, 247, 105));
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
    public Color getCanGoColor(){
        return canGoColor;
    }
    public Color getLightsquareColor(){
        return lightsquareColor;
    }
    public Color getDarksquareColor(){
        return darksquareColor;
    }

    public Color getWhatPieceColor(){
        return whatPieceColor;
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

    public String getPlayer1Avatar() {
        return player1Avatar;
    }

    public String getPlayer2Avatar() {
        return player2Avatar;
    }
}