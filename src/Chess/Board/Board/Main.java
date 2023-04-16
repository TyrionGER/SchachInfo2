package Chess.Board.Board;

import java.util.Scanner;

import Chess.Board.Figures.*;

public class Main {
    private static int whatmove = 1;
    private static int i = 1;
    private static int x, y, newX, newY;

    public static void main(String[] args) {
        Schachbrett.initializeBoard();
        Scanner scan = new Scanner(System.in);

        while (i == 1) {
            printSchachbrett(Schachbrett.board);
            System.out.println("Bitte Start X Feld angeben: ");
            x = scan.nextInt();
            System.out.println("Bitte Start Y Feld angeben: ");
            y = scan.nextInt();

            if (x < 0 || x > 7 || y < 0 || y > 7){
                //todo insert helpful message here
                continue;
            }
            if (Schachbrett.board[y][x] == null) {
                System.out.println("______Leeres Startfeld ausgewählt, neue Eingabe______");
                continue;
            }
            if (Schachbrett.board[y][x].getColor() == getCurrentColor()) {
                System.out.println("______Falsche Farbe oder Eingabe, Weiß war am Zug, neue Eingabe_______");
                continue;
            }

            System.out.println("Bitte Ziel X Feld angeben: ");
            newX = scan.nextInt();
            System.out.println("Bitte Ziel Y Feld angeben: ");
            newY = scan.nextInt();


            if (newX < 0 || newX > 7 || newY < 0 || newY > 7){
                //todo insert helpful message here
                continue;
            }

            var currentPiece = Schachbrett.board[y][x];
            if (currentPiece.move(newX, newY)) {
                currentPiece.Promote(newY, newX);
                Schachbrett.resetEnPassant(getCurrentColor());
                whatmove++;
            } else {
                System.out.println("______Zug nicht erlaubt_______");
            }
        }
    }

    public static Figure.Color getCurrentColor(){
        return (whatmove % 2 == 1 ? Figure.Color.White : Figure.Color.Black);
    }


    public static void printSchachbrett(Figure[][] schachbrett) {
        System.out.println();
        System.out.print("  ");
        System.out.print("____________________________________________________________________________________________________________________________");
        System.out.println();
        for (int row = 7; row >= 0; row--) {
            System.out.printf("%-5s", String.valueOf(row) + " | ");
            for (int col = 0; col < 8; col++) {
                if (schachbrett[row][col] != null) {
                    System.out.printf("%-15s", schachbrett[row][col].toString());
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

