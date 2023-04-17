package Chess.Board.Figures;

import Chess.Board.Board.Schachbrett;

import java.util.Scanner;

public class Pawn extends Figure{

    public Pawn(Color color, int x, int y) {
        super(color, x, y);
    }

    public boolean isPresentable;

    public boolean wasEnpassant;

    @Override
    protected boolean isValidMove(int targetX, int targetY) {
        wasEnpassant = false;
        //Check for backwards movement
        if (color == Color.White && targetY <= Ycord) {
            return false;
        }
        if (color == Color.Black && targetY >= Ycord) {
            return false;
        }
        //Allow move from baseline
        if (Math.abs(Ycord - targetY) == 2 && Ycord == (color == Color.White ? 1 : 6) && Xcord == targetX) {
            isPresentable = true;
            return true;
        }
        //Check for wrong move distance
        if (Math.abs(Ycord - targetY) != 1) {
            return false;
        }
        //Allow straight move
        if (Xcord == targetX && Schachbrett.board[targetY][targetX] == null) {
            return true;
        }

        //Allow enemy capture
        if (Math.abs(Xcord - targetX) == 1 && Schachbrett.board[targetY][targetX] != null && Schachbrett.board[targetY][targetX].color != color) {
            return true;
        }

        if (Schachbrett.getEnPassant(color)){
            int passYCord = targetY - (color == Color.White ? 1 : -1);
            if (Schachbrett.board[passYCord][targetX] instanceof Pawn tp){
                wasEnpassant = tp.isPresentable;
                return tp.isPresentable;
            }
        }

        return false;
    }



    @Override
    public boolean Promote(int newY, int newX) {
        if (Schachbrett.board[newY][newX] == Schachbrett.board[7][newX] || Schachbrett.board[newY][newX] == Schachbrett.board[0][newX]) {
            Figure obj = null;
            while (obj == null){
                System.out.println("Welche Figur wollen sie ?");
                System.out.println("Rook = 1, Queen = 2, Bishop = 3, Knight = 4");

                Scanner scan = new Scanner(System.in);
                int zahl = scan.nextInt();
                obj = switch (zahl) {
                    case 1 -> Schachbrett.board[newY][newX] = new Rook(getColor(), newX, newY);
                    case 2 -> Schachbrett.board[newY][newX] = new Queen(getColor(), newX, newY);
                    case 3 -> Schachbrett.board[newY][newX] = new Bishop(getColor(), newX, newY);
                    case 4 -> Schachbrett.board[newY][newX] = new Knight(getColor(), newX, newY);
                    default -> {
                        System.out.println("Ungueltige Eingabe.");
                        yield null;
                    }
                };
            }

            capture();
            Schachbrett.addPiece(obj, color);
            return true;
        }
        return false;
    }

    @Override
    public String getPiece() {
        return "Pawn";
    }

    @Override
    public boolean move(int newX, int newY) {
        int moveDist = Math.abs((newY - Ycord));
        if (wasEnpassant){
            if (Schachbrett.board[Ycord - (color == Color.White ? 1 : -1)][Xcord] != null){
                Schachbrett.board[Ycord - (color == Color.White ? 1 : -1)][Xcord].capture();
            }
        }
        if (super.move(newX, newY)){
            if (moveDist == 2) {
                Schachbrett.setEnPassant(color, this);
            }

            return true;
        }
        return false;
    }
}
