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

    static JLabel player1Timer = new JLabel();
    static JLabel player2Timer = new JLabel();

    Color creme = new Color(240, 217, 181);
    Color hellbraun = new Color(181, 136, 99);
    Color gelb = new Color(247, 247, 105);
    Color braun = new Color(104, 78, 57);

    public UI_UX() {
        setTitle("Schach");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
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
                    }
                }

                if ((i + j) % 2 == 0) {
                    button.setBackground(creme);
                } else {
                    button.setBackground(hellbraun);
                }

                button.setActionCommand( i + "," + j);
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String[] coordinates = e.getActionCommand().split(",");
                        int clickedY = Integer.parseInt(coordinates[0]);
                        int clickedX = Integer.parseInt(coordinates[1]);

                        Figure clickedPiece = Schachbrett.board[clickedY][clickedX];

                        if(clickedPiece != null){
                            Figure.Color clickedPieceColor = clickedPiece.getColor();

                            if(clickedPieceColor == Main.gameloop.getCurrentColor()) {
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
    }
    private void updateChessboard() {
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
            Main.gameloop.setCoordinates(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
            click = 0;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            updateChessboard();
        }
    }

    public static class chessTimer {
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
                    if (Main.gameloop.getCurrentColor() == Figure.Color.White) {
                        player1TimeLeft--;
                        if(player1TimeLeft == 0){
                            timer.cancel();
                            System.out.println("White Wins!");
                        }
                    }else{
                        player2TimeLeft--;
                        if(player2TimeLeft == 0){
                            timer.cancel();
                            System.out.println("Black Wins!");
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

    public static void startUI() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UI_UX();
            }
        });
    }
}