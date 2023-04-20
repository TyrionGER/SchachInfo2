package Chess.Board.Board;

import java.util.Scanner;

import Chess.Board.Figures.*;

public class Main {
    private static int whatmove = 1;
    private static int i = 1;
    private static int x, y, newX, newY;

    public static void main(String[] args){
        Schachbrett.initializeBoard();
        Scanner scan = new Scanner(System.in);

        while (i == 1) {
            printSchachbrett();
            System.out.println("Bitte Start X Feld angeben: ");
            x = scan.nextInt();
            System.out.println("Bitte Start Y Feld angeben: ");
            y = scan.nextInt();

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

            System.out.println("Bitte Ziel X Feld angeben: ");
            newX = scan.nextInt();
            System.out.println("Bitte Ziel Y Feld angeben: ");
            newY = scan.nextInt();


            if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
                //todo insert helpful message here
                continue;
            }
            var currentPiece = Schachbrett.board[y][x];
            Schachbrettmirror.initializeMirror();

            if (Schachbrettmirror.isInCheckAfterMove(getCurrentColor(), x, y, newX, newY)) {
                    System.out.println("Ungültiger Zug, eigener König wäre im Schach");
                    Schachbrettmirror.clearMirror();
            } else {
                if (currentPiece.move(newX, newY)) {
                    if ((currentPiece instanceof Pawn) && (newY == (currentPiece.getColor() == Figure.Color.White ? 7 : 0))) {
                        currentPiece.Promote(newX, newY);
                    }
                    Schachbrett.resetEnPassant(getCurrentColor());
                    //Check if King is in Check
                    for (Figure Piece : (getCurrentColor() == Figure.Color.White ? Schachbrett.blackPieces : Schachbrett.whitePieces)) {
                        if (Piece instanceof King) {
                            if (Schachbrett.isAttacked((getCurrentColor() == Figure.Color.White ? Figure.Color.Black : Figure.Color.White), Piece.getXcord(), Piece.getYcord())) {
                                System.out.println((getCurrentColor() == Figure.Color.White ? Figure.Color.Black : Figure.Color.White) + " König ist im Schach");
                            }
                        }
                    }
                    whatmove++;
                } else {
                    System.out.println("______Zug nicht erlaubt_______");
                }
                Schachbrettmirror.clearMirror();

            }
        }
    }
//todo Check if King can get out of Check / has possible moves and prohibit moves that set own King check --> Wincond, Castle
    public static Figure.Color getCurrentColor(){
        return (whatmove % 2 == 1 ? Figure.Color.White : Figure.Color.Black);
    }


    public static void printSchachbrett() {
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
}

