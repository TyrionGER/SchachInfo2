package Chess.Board.Figures;

import Chess.Board.Board.Schachbrett;

public class Rook extends Figure{

    public Rook(Color color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        //check if movement is straight
        if (Xcord != newX && Ycord != newY) {
            return false;
        }
        //Check for Pieces blocking the movement
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
            if (Ycord < newY) { //movement from bottom to top
                for (int i = Ycord + 1; i < newY; i++) {
                    if (Schachbrett.board[i][newX] != null) {
                        return false;
                    }

                }
            } else if (Ycord > newY) {//movement from top to bottom
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
        return "Rook";
    }

}
