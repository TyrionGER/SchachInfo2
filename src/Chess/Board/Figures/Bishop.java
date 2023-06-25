package Chess.Board.Figures;

import Chess.Board.Board.Schachbrett;
import Chess.Board.Board.Schachbrettmirror;

public class Bishop extends Figure{ //Bishop erbt von der Basisklasse Figure
    public Bishop(Color color, int x, int y) {
        super(color, x, y);
    } //Bishop Konstruktor und setzt die entsprechenden werte. (Ruft die Elternklasse auf [=super])

    @Override
    public boolean isValidMove(int newX, int newY) {
        if (Math.abs(newX - Xcord) != Math.abs(newY - Ycord)) { //da der Bishop nur diagonal sich bewegen kann wird überprüft ob X und Y gleich sind
            return false;
        } else if (newX - Xcord == newY - Ycord || newX - Xcord == -(newY - Ycord)) {
            int xDir = (newX - Xcord) > 0 ? 1 : -1; //Wenn [x/y]Dir > 0 dann setze xDir auf 1 sonst -1
            int yDir = (newY - Ycord) > 0 ? 1 : -1;

            int x = Xcord + xDir;
            int y = Ycord + yDir;

            while (x != newX && y != newY) {
                if (x < 0 || x > 7 || y < 0 || y > 7) {
                    break; // Out of bounds --> break while loop
                }
                if (Schachbrett.board[y][x] != null) {
                    return false;
                }

                x += xDir;
                y += yDir;
            }
        }
        //Check whether Piece is Moving onto an empty square
        if (Schachbrett.board[newY][newX] != null) {
            //return whether Square with piece is own color if yes return false
            return Schachbrett.board[newY][newX].color != this.color;
        }
        return true;
    }

    @Override
    public String getPiece() {
        return "Bishop";
    }

    public static class BishopMirror extends Bishop{ //Genau gleich wie Bishop nur für Mirror.

        public BishopMirror(Color color, int x, int y) {
            super(color, x, y);
        }
        @Override
        public boolean isValidMove(int newX, int newY){
            if (Math.abs(newX - Xcord) != Math.abs(newY - Ycord)) {
                return false;
            } else if (newX - Xcord == newY - Ycord || newX - Xcord == -(newY - Ycord)) {
                int xDir = (newX - Xcord) > 0 ? 1 : -1;
                int yDir = (newY - Ycord) > 0 ? 1 : -1;

                int x = Xcord + xDir;
                int y = Ycord + yDir;

                while (x != newX && y != newY) {
                    if (x < 0 || x > 7 || y < 0 || y > 7) {
                        break; // Out of bounds --> break while loop
                    }
                    if (Schachbrettmirror.board[y][x] != null) {
                        return false;
                    }

                    x += xDir;
                    y += yDir;
                }
            }
            //Check whether Piece is Moving onto an empty square
            if (Schachbrettmirror.board[newY][newX] != null) {
                //return whether Square with piece is own color if yes return false
                return Schachbrettmirror.board[newY][newX].color != this.color;
            }
            return true;
        };
    }
}
