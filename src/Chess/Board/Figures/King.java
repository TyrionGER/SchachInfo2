package Chess.Board.Figures;

import Chess.Board.Board.Schachbrett;

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
        //test ob Zielfeld frei ist
        if (Schachbrett.board[newY][newX] != null) {

            return false;
        }
        return true;
    }

    @Override
    public String getPiece() {
        return "King";
    }
}
