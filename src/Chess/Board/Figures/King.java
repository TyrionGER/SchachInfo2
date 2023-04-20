package Chess.Board.Figures;

import Chess.Board.Board.Schachbrett;
import Chess.Board.Board.Schachbrettmirror;

public class King extends Figure{

    public King(Color color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        //test ob Zug 1 Feld oder groeßer
        if (Math.abs(Xcord - newX) > 1 || Math.abs(Ycord - newY) > 1) {
            return false;
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
        return "King";
    }

    public static class KingMirror extends King {
        public KingMirror(Color color, int x, int y){
            super(color, x, y);
        }
        @Override
        public boolean isValidMove(int newX, int newY) {
            //test ob Zug 1 Feld oder groeßer
            if (Math.abs(Xcord - newX) > 1 || Math.abs(Ycord - newY) > 1) {
                return false;
            }
            //Check whether Piece is Moving onto an empty square
            if (Schachbrettmirror.board[newY][newX] != null) {
                //return whether Square with piece is own color if yes return false
                return Schachbrettmirror.board[newY][newX].color != this.color;
            }
            return true;
        }
    }
}
