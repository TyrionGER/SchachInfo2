package Chess.Board.Board;

import java.util.Scanner;

import Chess.Board.Figures.*;

public class Main {
    private static int whatmove = 1;
    private static int i = 1;
    private static int x = -1,y = -1,newX = -1, newY = -1;
    public static void main(String[] args) {
        Schachbrett Schachspiel = new Schachbrett();
        Schachfiguren.Schachfigur[][] Schach = Schachspiel.initializeSchachbrett();

        while(i == 1){
            Scanner scan = new Scanner(System.in);

            printSchachbrett(Schach);

            System.out.println("Bitte Start X Feld angeben: ");
            x = scan.nextInt();
            System.out.println("Bitte Start Y Feld angeben: ");
            y = scan.nextInt();
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
    public static void printSchachbrett(Schachfiguren.Schachfigur[][] schachbrett) {
        System.out.println();
        System.out.printf("  ");
        System.out.printf("____________________________________________________________________________________________________________________________");
        System.out.println();
        for (int row = 7; row >= 0; row--) {
            System.out.printf("%-5s", String.valueOf(row) + " | ");
            for (int col = 0; col < 8; col++) {
                if (schachbrett[row][col] != null) {
                    System.out.printf("%-15s", schachbrett[row][col].toString());
                } else {
                    System.out.printf("%-15s", "----------");
                }
                if(col == 7){
                    System.out.printf("|");
                }
            }
            System.out.println();
        }
        System.out.printf("  |");
            System.out.printf("__________________________________________________________________________________________________________________________|");
        System.out.println();
        for (int i = 0; i <= 7; i++) {
            System.out.printf("%-15s", "          " + String.valueOf(i));
        }

        System.out.println();
    }
}

