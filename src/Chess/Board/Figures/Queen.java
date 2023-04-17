package Chess.Board.Figures;

import Chess.Board.Board.Schachbrett;

public class Queen extends Figure{

    public Queen(Color color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        //Check if move is straight
        if (Xcord != newX && Ycord != newY) {
            //Check if move is diagonal
            if (Math.abs(newX - Xcord) != Math.abs(newY - Ycord)) {
                return false;
            } else if (newX - Xcord == newY - Ycord || newX - Xcord == -(newY - Ycord)) {
                int xDir = (newX - Xcord) > 0 ? 1 : -1;
                int yDir = (newY - Ycord) > 0 ? 1 : -1;
                int x = Xcord + xDir;
                int y = Ycord + yDir;

                //check if there are pieces blocking diagonal move
                while (x != newX && y != newY) {
                    if (Schachbrett.board[y][x] != null) {
                        return false;
                    }

                    x += xDir;
                    y += yDir;
                }
            }
        }

        //check if pieces block straight moves
        if (Xcord == newX || Ycord == newY) {
            if (Xcord < newX) { //Movement from left to right
                for (int i = Xcord + 1; i < newX; i++) {
                    if (Schachbrett.board[newY][i] != null) {
                        return false;
                    }
                }
            } else if (Xcord > newX) { //Movement from right to left
                for (int i = Xcord - 1; i > newX; i--) {
                    if (Schachbrett.board[newY][i] != null) {
                        return false;
                    }
                }

            }
            if (Ycord < newY) { //Movement from bottom to top
                for (int i = Ycord + 1; i < newY; i++) {
                    if (Schachbrett.board[i][newX] != null) {
                        return false;
                    }

                }
            } else if (Ycord > newY) {//Movement from top to bottom
                for (int i = Ycord - 1; i > newY; i--) {
                    if (Schachbrett.board[i][newX] != null) {
                        return false;
                    }
                }

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
        return "Queen";
    }
}
