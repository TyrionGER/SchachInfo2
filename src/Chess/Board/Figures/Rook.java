package Chess.Board.Figures;

import Chess.Board.Board.Schachbrett;

public class Rook extends Figure{

    public Rook(Color color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        //test ob Wagerecht oder senkrecht
        if (Xcord != newX && Ycord != newY) {
            return false;
        }
        //Test ob zwischen Start und endposition Figuren sind
        if (Xcord == newX || Ycord == newY) {
            if (Xcord < newX) { //Bewegung von links nach rechts
                for (int i = Xcord + 1; i < newX; i++) {
                    if (Schachbrett.board[newY][i] != null) {
                        return false;
                    }
                }
            } else if (Xcord > newX) { //Bewegung von rechts nach links
                for (int i = Xcord - 1; i > newX; i--) {
                    if (Schachbrett.board[newY][i] != null) {
                        return false;
                    }
                }

            }
            if (Ycord < newY) { //bewegung von unten nach oben
                for (int i = Ycord + 1; i < newY; i++) {
                    if (Schachbrett.board[i][newX] != null) {
                        return false;
                    }

                }
            } else if (Ycord > newY) {//bewegung von oben nach unten
                for (int i = Ycord - 1; i > newY; i--) {
                    if (Schachbrett.board[i][newX] != null) {
                        return false;
                    }
                }

            }


        }
        return true;
    }

}
