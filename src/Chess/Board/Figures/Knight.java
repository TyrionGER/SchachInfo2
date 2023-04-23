package Chess.Board.Figures;

import Chess.Board.Board.Schachbrett;
import Chess.Board.Board.Schachbrettmirror;

public class Knight extends Figure{
    public Knight(Color color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        int deltaX = Math.abs(newX - Xcord);
        int deltaY = Math.abs(newY - Ycord);
        //check whether piece is moving in the right pattern
        if ((deltaX == 2 && deltaY == 2) || (deltaX == 2 && deltaY == 2)) {

            //Check whether Piece is Moving onto an empty square
            if (Schachbrett.board[newY][newX] != null) {
                //return whether Square with piece is own color if yes return false
                return Schachbrett.board[newY][newX].color != this.color;
            }
            return true;
        }
        return false;
    }

    @Override
    public String getPiece() {
        return "Knight";
    }

    public static class KnightMirror extends Knight {
        public KnightMirror(Color color, int x, int y){
            super(color, x, y);
        }
        @Override
        public boolean isValidMove(int newX, int newY) {
            int deltaX = Math.abs(newX - Xcord);
            int deltaY = Math.abs(newY - Ycord);
            //check whether piece is moving in the right pattern
            if ((deltaX == 2 && deltaY == 2) || (deltaX == 2 && deltaY == 2)) {

                //Check whether Piece is Moving onto an empty square
                if (Schachbrettmirror.board[newY][newX] != null) {
                    //return whether Square with piece is own color if yes return false
                    return Schachbrettmirror.board[newY][newX].color != this.color;
                }
                return true;
            }
            return false;
        }
    }
}
