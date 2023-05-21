package Chess.Board.Board;

import Chess.Board.Figures.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;

public class UI_UX extends JFrame {
    private JButton[][] schachbrett;
    private int[] coordinates = new int[4];
    private int click;
    private int promoteint = -1;
    public static UI_UX instance;

    JLabel player1Timer = new JLabel();
    JLabel player2Timer = new JLabel();

    Color creme = new Color(240, 217, 181);
    Color hellbraun = new Color(181, 136, 99);
    Color gelb = new Color(247, 247, 105);
    Color braun = new Color(104, 78, 57);


    public UI_UX() {
        setTitle("Schach");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setResizable(false);
        JPanel panel = new JPanel(new GridLayout(8, 8));
        schachbrett = new JButton[8][8];

        JPanel borderPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        JPanel bottomPanel = new JPanel(new FlowLayout());

        JLabel player1 = new JLabel("Player 1: ");
        JLabel player2 = new JLabel("Player 2: ");

        topPanel.add(player2);
        topPanel.add(player2Timer);

        bottomPanel.add(player1);
        bottomPanel.add(player1Timer);

        borderPanel.add(topPanel, BorderLayout.NORTH);
        borderPanel.add(bottomPanel, BorderLayout.SOUTH);

        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(800, 800));
                if (Schachbrett.board != null) {
                    Figure piece = Schachbrett.board[i][j];
                    if (piece != null) {
                        Figure.Color color = piece.getColor();
                        if (piece instanceof Pawn) {
                            ImageIcon PawnIcon = (color == Figure.Color.White) ? new ImageIcon("Imgfiles/Figuren/whitePawn.png") : new ImageIcon("Imgfiles/Figuren/blackPawn.png");
                            button.setIcon(PawnIcon);
                        } else if (piece instanceof Rook) {
                            ImageIcon RookIcon = (color == Figure.Color.White) ? new ImageIcon("Imgfiles/Figuren/whiteRook.png") : new ImageIcon("Imgfiles/Figuren/blackRook.png");
                            button.setIcon(RookIcon);
                        } else if (piece instanceof Knight) {
                            ImageIcon KnightIcon = (color == Figure.Color.White) ? new ImageIcon("Imgfiles/Figuren/whiteKnight.png") : new ImageIcon("Imgfiles/Figuren/blackKnight.png");
                            button.setIcon(KnightIcon);
                        } else if (piece instanceof Bishop) {
                            ImageIcon BishopIcon = (color == Figure.Color.White) ? new ImageIcon("Imgfiles/Figuren/whiteBishop.png") : new ImageIcon("Imgfiles/Figuren/blackBishop.png");
                            button.setIcon(BishopIcon);
                        } else if (piece instanceof Queen) {
                            ImageIcon QueenIcon = (color == Figure.Color.White) ? new ImageIcon("Imgfiles/Figuren/whiteQueen.png") : new ImageIcon("Imgfiles/Figuren/blackQueen.png");
                            button.setIcon(QueenIcon);
                        } else if (piece instanceof King) {
                            ImageIcon KingIcon = (color == Figure.Color.White) ? new ImageIcon("Imgfiles/Figuren/whiteKing.png") : new ImageIcon("Imgfiles/Figuren/blackKing.png");
                            button.setIcon(KingIcon);
                        }
                    }
                }

                if ((i + j) % 2 == 0) {
                    button.setBackground(creme);
                } else {
                    button.setBackground(hellbraun);
                }
                chessTimer chessTimer = new chessTimer(900);
                chessTimer.start();

                button.setActionCommand( i + "," + j);
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String[] coordinates = e.getActionCommand().split(",");
                        int clickedY = Integer.parseInt(coordinates[0]);
                        int clickedX = Integer.parseInt(coordinates[1]);

                        Figure clickedPiece = Schachbrett.board[clickedY][clickedX];

                        if(clickedPiece != null){
                            Figure.Color clickedPieceColor = clickedPiece.getColor();

                            if(clickedPieceColor == MainFrame.gameloop.getCurrentColor()) {
                                schachbrett[clickedY][clickedX].setBackground(gelb);
                            }
                        }
                        handleClick(clickedY, clickedX);
                    }
                });

                schachbrett[i][j] = button;
                panel.add(button);
            }
        }
        borderPanel.setBorder(BorderFactory.createLineBorder(braun, 50));
        borderPanel.add(panel, BorderLayout.CENTER);
        setContentPane(borderPanel);
        setVisible(true);

        centerWindow(this);
    }
    public void updateChessboard() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                JButton button = schachbrett[i][j];
                button.setPreferredSize(new Dimension(50, 50));
                if (Schachbrett.board != null) {
                    Figure piece = Schachbrett.board[i][j];
                    if (piece != null) {
                        Figure.Color color = piece.getColor();
                        if (piece instanceof Pawn) {
                            ImageIcon PawnIcon = (color == Figure.Color.White) ? new ImageIcon("Imgfiles/Figuren/whitePawn.png") : new ImageIcon("Imgfiles/Figuren/blackPawn.png");
                            button.setIcon(PawnIcon);
                        } else if (piece instanceof Rook) {
                            ImageIcon RookIcon = (color == Figure.Color.White) ? new ImageIcon("Imgfiles/Figuren/whiteRook.png") : new ImageIcon("Imgfiles/Figuren/blackRook.png");
                            button.setIcon(RookIcon);
                        } else if (piece instanceof Knight) {
                            ImageIcon KnightIcon = (color == Figure.Color.White) ? new ImageIcon("Imgfiles/Figuren/whiteKnight.png") : new ImageIcon("Imgfiles/Figuren/blackKnight.png");
                            button.setIcon(KnightIcon);
                        } else if (piece instanceof Bishop) {
                            ImageIcon BishopIcon = (color == Figure.Color.White) ? new ImageIcon("Imgfiles/Figuren/whiteBishop.png") : new ImageIcon("Imgfiles/Figuren/blackBishop.png");
                            button.setIcon(BishopIcon);
                        } else if (piece instanceof Queen) {
                            ImageIcon QueenIcon = (color == Figure.Color.White) ? new ImageIcon("Imgfiles/Figuren/whiteQueen.png") : new ImageIcon("Imgfiles/Figuren/blackQueen.png");
                            button.setIcon(QueenIcon);
                        } else if (piece instanceof King) {
                            ImageIcon KingIcon = (color == Figure.Color.White) ? new ImageIcon("Imgfiles/Figuren/whiteKing.png") : new ImageIcon("Imgfiles/Figuren/blackKing.png");
                            button.setIcon(KingIcon);
                        }
                    } else {
                        button.setIcon(null);
                    }
                }
                if ((i + j) % 2 == 0) {
                    button.setBackground(creme);
                } else {
                    button.setBackground(hellbraun);
                }
            }
        }
    }


    private void handleClick(int clickedY, int clickedX) {
        click++;
        // coordinates[0] -> Startreihe
        // coordinates[1] -> Startspalte
        // coordinates[2] -> Zielreihe
        // coordinates[3] -> Zielspalte
        if (click == 1) {
            coordinates[0] = clickedX;
            coordinates[1] = clickedY;
        } else if (click == 2) {
            coordinates[2] = clickedX;
            coordinates[3] = clickedY;
            MainFrame.gameloop.setCoordinates(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
            click = 0;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            updateChessboard();
        }
    }

    public class chessTimer {
        Timer timer;
        int player1TimeLeft;
        int player2TimeLeft;

        public chessTimer(int seconds){
            player1TimeLeft = seconds;
            player2TimeLeft = seconds;

        }

        public void start(){
            timer = new Timer();

            String player1Time = getPlayer1Time();
            String player2Time = getPlayer2Time();

            System.out.print("\rPlayer 1: " + player1Time);
            System.out.print("    Player 2: " + player2Time);

            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (MainFrame.gameloop.getCurrentColor() == Figure.Color.White) {
                        player1TimeLeft--;
                        if(player1TimeLeft == 0){
                            timer.cancel();
                            UI_UX.Endwindow("White Wins on time!");
                        }
                    }else{
                        player2TimeLeft--;
                        if(player2TimeLeft == 0){
                            timer.cancel();
                            UI_UX.Endwindow("Black Wins on time!");
                        }
                    }
                    player1Timer.setText(getPlayer1Time());
                    player2Timer.setText(getPlayer2Time());
                    String updatedPlayer1Time = getPlayer1Time();
                    String updatedPlayer2Time = getPlayer2Time();

                    System.out.print("\rPlayer 1: " + updatedPlayer1Time);
                    System.out.print("    Player 2: " + updatedPlayer2Time);
                }
            },0,1000);
        }

        public String getPlayer1Time() {
            int minutes = player1TimeLeft / 60;
            int seconds = player1TimeLeft % 60;
            return String.format("%02d:%02d", minutes, seconds);
        }

        public String getPlayer2Time() {
            int minutes = player2TimeLeft / 60;
            int seconds = player2TimeLeft % 60;
            return String.format("%02d:%02d", minutes, seconds);
        }
    }


    public static UI_UX getInstance() {
        if (instance == null) {
            instance = new UI_UX();
        }
        return instance;
    }
    public int openPromotionWindow() {
        promoteint = -1;
        JFrame promotionWindow = new JFrame("Promotion");
        promotionWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        promotionWindow.setSize(400, 200);
        promotionWindow.setLayout(new GridLayout(1, 4));

        JButton queenButton = new JButton();
        queenButton.setIcon(new ImageIcon("Imgfiles/Figuren/whiteQueen.png")); // oder blackQueen.png für schwarze Figuren
        queenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                promotionWindow.dispose();
                promoteint = 2;
            }
        });
        promotionWindow.add(queenButton);

        JButton rookButton = new JButton();
        rookButton.setIcon(new ImageIcon("Imgfiles/Figuren/whiteRook.png")); // oder blackRook.png für schwarze Figuren
        rookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                promotionWindow.dispose();
                promoteint = 1; // Rook
            }
        });
        promotionWindow.add(rookButton);

        JButton knightButton = new JButton();
        knightButton.setIcon(new ImageIcon("Imgfiles/Figuren/whiteKnight.png")); // oder blackKnight.png für schwarze Figuren
        knightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                promotionWindow.dispose();
                promoteint = 4; // Knight
            }
        });
        promotionWindow.add(knightButton);

        JButton bishopButton = new JButton();
        bishopButton.setIcon(new ImageIcon("Imgfiles/Figuren/whiteBishop.png")); // oder blackBishop.png für schwarze Figuren
        bishopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                promotionWindow.dispose();
                promoteint = 3; // Bishop
            }
        });
        promotionWindow.add(bishopButton);

        promotionWindow.setVisible(true);

        int promoteValue = -1; // Set initial value to -1

        while (promoteValue == -1) {
            // Wait until promoteValue changes from -1
            try {
                Thread.sleep(100); // Sleep for 100 milliseconds before checking again
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            promoteValue = promoteint; // Update promoteValue
        }

        return promoteValue;
    }
    public static void Endwindow(String endMessage) {
        JFrame endWindow = new JFrame("Spielende");
        endWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endWindow.setSize(400, 200);
        endWindow.setLayout(new GridLayout(2, 1));

        JLabel messageLabel = new JLabel(endMessage, SwingConstants.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton mainMenuButton = new JButton("Hauptmenü");
        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Schachbrett.board = null;
                endWindow.setVisible(false);
                MainFrame.restart();

            }
        });
        buttonPanel.add(mainMenuButton);

        JButton quitButton = new JButton("Spiel beenden");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(quitButton);

        endWindow.add(messageLabel);
        endWindow.add(buttonPanel);
        endWindow.setVisible(true);
        centerWindow(endWindow); // Zentriere das Fenster
    }
    private static void centerWindow(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int windowWidth = frame.getWidth();
        int windowHeight = frame.getHeight();
        int posX = (screenWidth - windowWidth) / 2;
        int posY = (screenHeight - windowHeight) / 2;
        frame.setLocation(posX, posY);
    }


}