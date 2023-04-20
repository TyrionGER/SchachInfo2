package Chess.Board.Figures;

import Chess.Board.Board.*;

public abstract class Figure {
    Figure mirrorPiece;

    public enum Called{
        FromBoard,
        FromMirror
    }

    public enum Color {
        White,
        Black
    }

    public enum Type {
        Bishop,
        King,
        Knight,
        Pawn,
        Queen,
        Rook,
    }

    protected final Color color;
    protected int Xcord;
    protected int Ycord;

    public Figure(Color color, int x, int y) {
        this.color = color;
        this.Xcord = x;
        this.Ycord = y;
    }

    protected abstract boolean isValidMove(int newX, int newY);

    public boolean move(int newX, int newY){
        if (isValidMove(newX, newY)) {
            Schachbrett.board[Ycord][Xcord] = null;
            Xcord = newX;
            Ycord = newY;
            if (this instanceof Pawn tp && tp.wasEnpassant){
                if (Schachbrett.board[Ycord - (color == Color.White ? 1 : -1)][Xcord] != null){
                    Schachbrett.board[Ycord - (color == Color.White ? 1 : -1)][Xcord].capture();
                    Schachbrett.board[Ycord - (color == Color.White ? 1 : -1)][Xcord] = null;
                }
            }
            Schachbrett.board[Ycord][Xcord] = this;
            return true;
        }
        return false;
    }
    //todo Pieces can still move upon their own color
    public boolean movemirror(int newX, int newY) {
        if (isValidMove(newX, newY)) {
            Schachbrettmirror.board[Ycord][Xcord] = null;
            Xcord = newX;
            Ycord = newY;
            if (this instanceof Pawn tp && tp.wasEnpassant) {
                if (Schachbrettmirror.board[Ycord - (color == Color.White ? 1 : -1)][Xcord] != null) {
                    Schachbrettmirror.board[Ycord - (color == Color.White ? 1 : -1)][Xcord].capturemirror();
                    Schachbrettmirror.board[Ycord - (color == Color.White ? 1 : -1)][Xcord] = null;
                }
            }
            Schachbrettmirror.board[Ycord][Xcord] = this;
            return true;
        }
        return false;
    }
    public boolean Promote(int newX, int newY) {
        return false;
    }

    public void capture(){
        if (color == Color.White){
            Schachbrett.whitePieces.remove(this);
        }
        if (color == Color.Black){
            Schachbrett.blackPieces.remove(this);
        }
    }
    public void capturemirror() {
        if (color == Color.White) {
            Schachbrettmirror.whitePieces.remove(this);
        }
        if (color == Color.Black) {
            Schachbrettmirror.blackPieces.remove(this);
        }
    }

    //region Getter

    public Color getColor() {
        return this.color;
    }

    public abstract String getPiece();
    @Override
    public String toString(){
        return this.color + this.getPiece();

    }

    public boolean isValidMoveGetter(int X, int Y){
        return isValidMove(X, Y);

    }
    public int getXcord(){
        return Xcord;
    }
    public int getYcord(){
        return Ycord;
    }

    //endregion


}

