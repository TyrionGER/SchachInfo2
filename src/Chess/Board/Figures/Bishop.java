package Chess.Board.Figures;

import Chess.Board.Board.Schachbrett;

public class Bishop extends Figure{
    public Bishop(Color color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        if (Math.abs(newX - Xcord) != Math.abs(newY - Ycord)) {
            return false;
        } else if (newX - Xcord == newY - Ycord || newX - Xcord == -(newY - Ycord)) {
            int xDir = (newX - Xcord) > 0 ? 1 : -1;
            int yDir = (newY - Ycord) > 0 ? 1 : -1;

            int x = Xcord + xDir;
            int y = Ycord + yDir;

            while (x != newX && y != newY) {
                if (Schachbrett.board[y][x] != null) {
                    return false;
                }

                x += xDir;
                y += yDir;
            }
        }
        return true;
    }

}