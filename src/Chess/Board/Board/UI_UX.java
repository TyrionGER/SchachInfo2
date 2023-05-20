package Chess.Board.Board;

import Chess.Board.Figures.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UI_UX extends JFrame {
    private JButton[][] schachbrett;
    private int[] coordinates = new int[4];
    private int click;

    public UI_UX() {
        setTitle("Schach");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        JPanel panel = new JPanel(new GridLayout(8, 8));
        schachbrett = new JButton[8][8];

        Color creme = new Color(235, 236, 208);
        Color gruen = new Color(119, 149, 86);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(50, 50));
                if (Schachbrett.board != null) {
                    Figure piece = Schachbrett.board[i][j];
                    if (piece != null) {
                        Figure.Color color = piece.getColor();
                        if (piece instanceof Pawn) {
                            ImageIcon PawnIcon = (color == Figure.Color.Black) ? new ImageIcon("Imgfiles/Figuren/whitePawn.png") : new ImageIcon("Imgfiles/Figuren/blackPawn.png");
                            button.setIcon(PawnIcon);
                        } else if (piece instanceof Rook) {
                            ImageIcon RookIcon = (color == Figure.Color.Black) ? new ImageIcon("Imgfiles/Figuren/whiteRook.png") : new ImageIcon("Imgfiles/Figuren/blackRook.png");
                            button.setIcon(RookIcon);
                        } else if (piece instanceof Knight) {
                            ImageIcon KnightIcon = (color == Figure.Color.Black) ? new ImageIcon("Imgfiles/Figuren/whiteKnight.png") : new ImageIcon("Imgfiles/Figuren/blackKnight.png");
                            button.setIcon(KnightIcon);
                        } else if (piece instanceof Bishop) {
                            ImageIcon BishopIcon = (color == Figure.Color.Black) ? new ImageIcon("Imgfiles/Figuren/whiteBishop.png") : new ImageIcon("Imgfiles/Figuren/blackBishop.png");
                            button.setIcon(BishopIcon);
                        } else if (piece instanceof Queen) {
                            ImageIcon QueenIcon = (color == Figure.Color.Black) ? new ImageIcon("Imgfiles/Figuren/whiteQueen.png") : new ImageIcon("Imgfiles/Figuren/blackQueen.png");
                            button.setIcon(QueenIcon);
                        } else if (piece instanceof King) {
                            ImageIcon KingIcon = (color == Figure.Color.Black) ? new ImageIcon("Imgfiles/Figuren/whiteKing.png") : new ImageIcon("Imgfiles/Figuren/blackKing.png");
                            button.setIcon(KingIcon);
                        }
                    }
                }

                if ((i + j) % 2 == 0) {
                    button.setBackground(creme);
                } else {
                    button.setBackground(gruen);
                }

                button.setActionCommand((7 - i) + "," + j);
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String[] coordinates = e.getActionCommand().split(",");
                        int clickedY = Integer.parseInt(coordinates[0]);
                        int clickedX = Integer.parseInt(coordinates[1]);
                        handleClick(clickedY, clickedX);

                    }
                });

                schachbrett[i][j] = button;
                panel.add(button);
            }
        }

        add(panel);
        setVisible(true);
    }

    private void handleClick(int clickedY, int clickedX) {
        click++;
        if (click == 1) {
            coordinates[0] = clickedX;
            coordinates[1] = clickedY;
        } else if (click == 2) {
            coordinates[2] = clickedX;
            coordinates[3] = clickedY;
            Main.gameloop.setCoordinates(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
            click = 0;
        }
    }

    private void handleMove(int[] coordinates) {

        // Verarbeiten Sie den Zug basierend auf den Koordinaten
        // coordinates[0] -> Startreihe
        // coordinates[1] -> Startspalte
        // coordinates[2] -> Zielreihe
        // coordinates[3] -> Zielspalte
    }

    public static void startUI() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UI_UX();
            }
        });
    }
}