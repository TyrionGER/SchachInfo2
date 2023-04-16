package Chess.Board.Figures;

import Chess.Board.Board.Schachbrett;

public class Knight extends Figure{
    public Knight(Color color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        int deltaX = Math.abs(newX - Xcord);
        int deltaY = Math.abs(newY - Ycord);
        if ((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2)) {
            //Test ob Zielfeld frei ist
            if (Schachbrett.board[newX][newY] != null) {
                return false;
            }
            return true;
        }
        return false;
    }

}
