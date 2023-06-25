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
    private Figure selectedPiece; // Aktuell ausgewählte Figur für Drag-and-Drop
    private int dragStartX, dragStartY; // Startkoordinaten für Drag-and-Drop
    public static UI_UX instance;
    private static String player1Name,player2Name,chessPieceDesign;
    private static Color lightSquareColor,darkSquareColor;
    JLabel player1Timer = new JLabel();
    JLabel player2Timer = new JLabel();
    JLabel player1;
    JLabel player2;
    Color gelb = new Color(247, 247, 105);
    Color dunkelgrau = new Color(43, 43, 43);

    Color gruen = new Color(0,238,0);
    Color rot = new Color(255,0,0);

    public int designX;

     // X == 4, Y == 12
    String[][] chessboardDesign = {
            { //DEFAULT
                    "Imgfiles/Figuren/whitePawn.png",   //0
                    "Imgfiles/Figuren/whiteRook.png",   //1
                    "Imgfiles/Figuren/whiteKnight.png", //2
                    "Imgfiles/Figuren/whiteBishop.png", //3
                    "Imgfiles/Figuren/whiteQueen.png",  //4
                    "Imgfiles/Figuren/whiteKing.png",   //5
                    "Imgfiles/Figuren/blackPawn.png",   //6
                    "Imgfiles/Figuren/blackRook.png",   //7
                    "Imgfiles/Figuren/blackKnight.png", //8
                    "Imgfiles/Figuren/blackBishop.png", //9
                    "Imgfiles/Figuren/blackQueen.png",  //10
                    "Imgfiles/Figuren/blackKing.png"    //11
            },
            { //WOOD
                    "Imgfiles/Figuren/woodWhitePawn.png",
                    "Imgfiles/Figuren/woodWhiteRook.png",
                    "Imgfiles/Figuren/woodWhiteKnight.png",
                    "Imgfiles/Figuren/woodWhiteBishop.png",
                    "Imgfiles/Figuren/woodWhiteQueen.png",
                    "Imgfiles/Figuren/woodWhiteKing.png",
                    "Imgfiles/Figuren/woodBlackPawn.png",
                    "Imgfiles/Figuren/woodBlackRook.png",
                    "Imgfiles/Figuren/woodBlackKnight.png",
                    "Imgfiles/Figuren/woodBlackBishop.png",
                    "Imgfiles/Figuren/woodBlackQueen.png",
                    "Imgfiles/Figuren/woodBlackKing.png"
            },
            { //OLD
                    "Imgfiles/Figuren/oldWhitePawn.png",
                    "Imgfiles/Figuren/oldWhiteRook.png",
                    "Imgfiles/Figuren/oldWhiteKnight.png",
                    "Imgfiles/Figuren/oldWhiteBishop.png",
                    "Imgfiles/Figuren/oldWhiteQueen.png",
                    "Imgfiles/Figuren/oldWhiteKing.png",
                    "Imgfiles/Figuren/oldBlackPawn.png",
                    "Imgfiles/Figuren/oldBlackRook.png",
                    "Imgfiles/Figuren/oldBlackKnight.png",
                    "Imgfiles/Figuren/oldBlackBishop.png",
                    "Imgfiles/Figuren/oldBlackQueen.png",
                    "Imgfiles/Figuren/oldBlackKing.png"
            },
            { //MODERN
                    "Imgfiles/Figuren/modernWhitePawn.png",
                    "Imgfiles/Figuren/modernWhiteRook.png",
                    "Imgfiles/Figuren/modernWhiteKnight.png",
                    "Imgfiles/Figuren/modernWhiteBishop.png",
                    "Imgfiles/Figuren/modernWhiteQueen.png",
                    "Imgfiles/Figuren/modernWhiteKing.png",
                    "Imgfiles/Figuren/modernBlackPawn.png",
                    "Imgfiles/Figuren/modernBlackRook.png",
                    "Imgfiles/Figuren/modernBlackKnight.png",
                    "Imgfiles/Figuren/modernBlackBishop.png",
                    "Imgfiles/Figuren/modernBlackQueen.png",
                    "Imgfiles/Figuren/modernBlackKing.png"
            }
};

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
        player1 = new JLabel(player1Name);
        player2 = new JLabel(player2Name);

        topPanel.add(player2);
        topPanel.add(player2Timer);
        topPanel.setBackground(dunkelgrau);

        bottomPanel.add(player1);
        bottomPanel.add(player1Timer);
        bottomPanel.setBackground(dunkelgrau);

        borderPanel.add(topPanel, BorderLayout.NORTH);
        borderPanel.add(bottomPanel, BorderLayout.SOUTH);

        getSettings();


        switch(chessPieceDesign){
            case "Default":
                designX = 0;
                break;
            case "Wood":
                designX = 1;
                break;
            case "Old":
                designX = 2;
                break;
            case "Modern":
                designX = 3;
                break;

        }

        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(800, 800));
                if (Schachbrett.board != null) {
                    Figure piece = Schachbrett.board[i][j];
                    if (piece != null) {
                        Figure.Color color = piece.getColor();
                        if (piece instanceof Pawn) {
                            ImageIcon PawnIcon = (color == Figure.Color.White) ? new ImageIcon(chessboardDesign[designX][0]) : new ImageIcon(chessboardDesign[designX][6]);
                            button.setIcon(PawnIcon);
                        } else if (piece instanceof Rook) {
                            ImageIcon RookIcon = (color == Figure.Color.White) ? new ImageIcon(chessboardDesign[designX][1]) : new ImageIcon(chessboardDesign[designX][7]);
                            button.setIcon(RookIcon);
                        } else if (piece instanceof Knight) {
                            ImageIcon KnightIcon = (color == Figure.Color.White) ? new ImageIcon(chessboardDesign[designX][2]) : new ImageIcon(chessboardDesign[designX][8]);
                            button.setIcon(KnightIcon);
                        } else if (piece instanceof Bishop) {
                            ImageIcon BishopIcon = (color == Figure.Color.White) ? new ImageIcon(chessboardDesign[designX][3]) : new ImageIcon(chessboardDesign[designX][9]);
                            button.setIcon(BishopIcon);
                        } else if (piece instanceof Queen) {
                            ImageIcon QueenIcon = (color == Figure.Color.White) ? new ImageIcon(chessboardDesign[designX][4]) : new ImageIcon(chessboardDesign[designX][10]);
                            button.setIcon(QueenIcon);
                        } else if (piece instanceof King) {
                            ImageIcon KingIcon = (color == Figure.Color.White) ? new ImageIcon(chessboardDesign[designX][5]) : new ImageIcon(chessboardDesign[designX][11]);
                            button.setIcon(KingIcon);
                        }
                    }
                }

                if ((i + j) % 2 == 0) {
                    button.setBackground(lightSquareColor);
                } else {
                    button.setBackground(darkSquareColor);
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
                                for (int a = 0; a < 8; a++) {
                                    for (int b = 0; b < 8; b++) {
                                        if (clickedPiece.isValidMoveGetter(a, b)){
                                            schachbrett[b][a].setBackground(gruen);
                                            /*
                                                Figure.Color enemyColor = (clickedPieceColor == Figure.Color.White) ? Figure.Color.Black : Figure.Color.White;
                                                    if(clickedPieceColor != enemyColor){
                                                    //public void class Lennart{};
                                                         schachbrett[b][a].setBackground(rot);
                                            }

                                             */

                                        }
                                    }
                                }
                            }
                        }
                        handleClick(clickedY, clickedX);
                    }
                });

                schachbrett[i][j] = button;
                panel.add(button);
            }
        }
        borderPanel.setBorder(BorderFactory.createLineBorder(dunkelgrau, 50));
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
                button.setTransferHandler(new TransferHandler("icon"));
                button.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        JButton button = (JButton) e.getSource();
                        selectedPiece = (Figure) button.getClientProperty("figure");
                        if (selectedPiece != null) {
                            dragStartX = button.getX();
                            dragStartY = button.getY();
                            button.getTransferHandler().exportAsDrag(button, e, TransferHandler.COPY);
                        }
                    }
                });
                button.addMouseMotionListener(new MouseMotionAdapter() {
                    public void mouseDragged(MouseEvent e) {
                        JButton button = (JButton) e.getSource();
                        TransferHandler handler = button.getTransferHandler();
                        if (handler != null) {
                       /*     if(selectedPiece.isValidMoveGetter(endX,endY)){
                                MainFrame.gameloop.setCoordinates(dragStartX,dragStartY,endX,endY);
                                handler.exportAsDrag(button, e, TransferHandler.COPY);
                                updateChessboard();
                            }*/
                            //public void class Lennart2{};
                        }
                    }
                });
                if ((i + j) % 2 == 0) {
                    button.setBackground(lightSquareColor);
                } else {
                    button.setBackground(darkSquareColor);
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
            updateChessboard(); //Board MUSS hier geaupdated werden sonst kommt es zu komplikationen mit dem anzeigen von möglichen zügen
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
                        // Ändere die Farbe des Textes für den aktuellen Spieler
                        player1.setForeground(Color.RED);
                        player2.setForeground(Color.WHITE);
                        if(player1TimeLeft == 0){
                            timer.cancel();
                            UI_UX.Endwindow("White Wins on time!");
                        }
                    }else{
                        player2TimeLeft--;
                        player1.setForeground(Color.WHITE);
                        player2.setForeground(Color.RED);
                        if(player2TimeLeft == 0){
                            timer.cancel();
                            UI_UX.Endwindow("Black Wins on time!");
                        }
                    }
                    player1Timer.setText(getPlayer1Time());
                    player2Timer.setText(getPlayer2Time());

                    player1Timer.setForeground(Color.WHITE);
                    player2Timer.setForeground(Color.WHITE);

                    System.out.print("\rPlayer 1: " + player1Time);
                    System.out.print("    Player 2: " + player2Time);
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

    public static void getSettings(){
       MainFrame frame = MainFrame.getInstance();
        instance.player1Name = frame.getPlayer1Name();
        instance.player2Name = frame.getPlayer2Name();
        instance.chessPieceDesign = frame.getChessPieceDesign();
        instance.lightSquareColor = frame.getLightsquareColor();
        instance.darkSquareColor = frame.getDarksquareColor();
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