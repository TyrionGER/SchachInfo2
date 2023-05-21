package Chess.Board.Board;

import Chess.Board.Figures.*;
import java.awt.event.ActionEvent;
import java.util.Scanner;

public class GameloopController{
    private int whatmove = 1;
    private int i = 1;
    private int x, y, newX, newY;
    private boolean isFirstClick = true;

    public void Startchess() {
        Schachbrett.initializeBoard();
        Scanner scan = new Scanner(System.in);
        UI_UX UI = UI_UX.getInstance(); // Statt UI_UX.startUI();

        while (i == 1) {
            x = -1;
            y = -1;
            newX = -1;
            newY = -1;
            printSchachbrett();
            while( x == -1 || y == -1 || newX == -1 || newY == -1){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }






            if (x < 0 || x > 7 || y < 0 || y > 7) {
                System.out.println("x oder y zu hoch oder zu niedrig");
                continue;
            }
            if (Schachbrett.board[y][x] == null) {
                System.out.println("______Leeres Startfeld ausgewählt, neue Eingabe______");
                continue;
            }
            if (Schachbrett.board[y][x].getColor() != getCurrentColor()) {
                System.out.println("______Falsche Farbe " + getCurrentColor() + " war am Zug, neue Eingabe_______");
                continue;
            }


            if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
                System.out.println("x oder y zu hoch oder zu niedrig");
                continue;
            }
            var currentPiece = Schachbrett.board[y][x];

            Schachbrettmirror.initializeMirror();

            if (Schachbrettmirror.isInCheckAfterMove(getCurrentColor(), x, y, newX, newY)) {
                System.out.println("Ungültiger Zug oder illegaler Zug");
                Schachbrettmirror.clearMirror();
            } else {
                if (currentPiece.move(newX, newY)) {
                    if ((currentPiece instanceof Pawn) && (newY == (currentPiece.getColor() == Figure.Color.White ? 7 : 0))) {
                        currentPiece.Promote(newX, newY);
                    }
                    //Da enPassant nur genau nach dem Doppelzug möglich ist wird hier für die andere Farbe Enpassant zurückgesetzt
                    Schachbrett.resetEnPassant(getCurrentColor());
                    //Test ob gegnerischer König im Schach, Matt, Patt
                    for (Figure Piece : (getCurrentColor() == Figure.Color.White ? Schachbrett.blackPieces : Schachbrett.whitePieces)) { //Hier wird durch die Listen der Figuren der jeweiligen Farbe durchiteriert
                        if (Piece instanceof King) {
                            if (Schachbrett.isAttacked((getCurrentColor() == Figure.Color.White ? Figure.Color.Black : Figure.Color.White), Piece.getXcord(), Piece.getYcord())) { //test ob das Feld auf dem der gegnerische König steht angegriffen wird
                                System.out.println((getCurrentColor() == Figure.Color.White ? Figure.Color.Black : Figure.Color.White) + " König ist im Schach");
                                if(Schachbrettmirror.isMatt(getCurrentColor(),Piece.getXcord(), Piece.getYcord() )) { //Test ob es einen Legalen zug gibt nachdem der Gegnerische König nichtmehr im Schach ist
                                    System.out.println((getCurrentColor() == Figure.Color.White ? Figure.Color.Black : Figure.Color.White) + " König ist Matt, " + getCurrentColor() + " hat gewonnen!");
                                    printSchachbrett();
                                    i = 0;
                                }
                            }
                            Schachbrettmirror.clearMirror();
                            Schachbrettmirror.initializeMirror();
                            if(Schachbrettmirror.Patt(getCurrentColor() == Figure.Color.White ? Figure.Color.Black : Figure.Color.White, Piece.getXcord(), Piece.getYcord()) && i != 0){// Patt wenn der gegnerische Spieler keinen legalen zug mehr hat aber nicht im Schach ist
                                System.out.println("Patt, kein legaler Zug möglich!");
                                printSchachbrett();
                                i = 0;
                            }
                        }
                    }
                    whatmove++;
                    UI.updateChessboard();
                } else {
                    System.out.println("______Zug nicht erlaubt_______");
                }
                Schachbrettmirror.clearMirror();

            }
        }
    }

    public Figure.Color getCurrentColor() {
        return (whatmove % 2 == 1 ? Figure.Color.White : Figure.Color.Black);
    }


    public void printSchachbrett() {
        System.out.println();
        System.out.print("  ");
        System.out.print("____________________________________________________________________________________________________________________________");
        System.out.println();
        for (int row = 7; row >= 0; row--) {
            System.out.printf("%-5s", String.valueOf(row) + " | ");
            for (int col = 0; col < 8; col++) {
                if (Schachbrett.board[row][col] != null) {
                    System.out.printf("%-15s", Schachbrett.board[row][col].toString());
                } else {
                    System.out.printf("%-15s", "----------");
                }
                if (col == 7) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
        System.out.print("  |");
        System.out.print("__________________________________________________________________________________________________________________________|");
        System.out.println();
        for (int i = 0; i <= 7; i++) {
            System.out.printf("%-15s", "          " + String.valueOf(i));
        }
        System.out.println();
    }
    public void setCoordinates(int x, int y, int newX, int newY) {
        this.x = x;
        this.y = y;
        this.newX = newX;
        this.newY = newY;
    }

    /*
    private void setFirstClick(boolean isFirstClick) {
        this.isFirstClick = isFirstClick;
    }
    private boolean isFirstClick() {
        return isFirstClick;
    }
    public void actionPerformed(ActionEvent e) {
        String[] coordinates = e.getActionCommand().split(",");
        y = Integer.parseInt(coordinates[0]);
        x = Integer.parseInt(coordinates[1]);
        Figure[][] board = Schachbrett.getBoard();
        if (board[y][x].getColor() == getCurrentColor()) {
            y = Integer.parseInt(coordinates[0]);
            x = Integer.parseInt(coordinates[1]);

        }

        if (isFirstClick) {
            if (board[y][x].getColor() == getCurrentColor()) {
                y = Integer.parseInt(coordinates[0]);
                x = Integer.parseInt(coordinates[1]);
                isFirstClick = false;
            }
        } else {
            if ((board[y][x] == null) || board[y][x].getColor() != getCurrentColor()) {
                move(y, x);
                isFirstClick = true;
            }
        }
    }
     */
}

