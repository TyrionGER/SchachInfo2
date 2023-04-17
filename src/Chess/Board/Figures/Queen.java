package Chess.Board.Figures;

import Chess.Board.Board.Schachbrett;

public class Queen extends Figure{

    public Queen(Color color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        //test ob Wagerecht, Senkrecht
        if (Xcord != newX && Ycord != newY) {
            //test ob Diagonal
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

    @Override
    public String getPiece() {
        return "Queen";
    }
}
