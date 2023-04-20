package Chess.Board.Board;

import Chess.Board.Figures.*;

import java.util.ArrayList;
import java.util.List;

public class Schachbrettmirror {
    public static Figure[][] board;
    public static boolean enPassantForWhite = false;
    public static boolean enPassantForBlack = false;

    public static Pawn whitePassantPawn;
    public static Pawn blackPassantPawn;

    public static List<Figure> whitePieces = new ArrayList<>();
    public static List<Figure> blackPieces = new ArrayList<>();

    public static void initializeMirror(){

        board = new Figure[8][8];


        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Figure piece = Schachbrett.board[y][x];
                if (piece != null) {
                    Figure.Color color = piece.getColor();
                    if (piece instanceof Pawn) {
                        Schachbrettmirror.board[y][x] = new Pawn.PawnMirror(color, x, y);
                    } else if (piece instanceof Rook) {
                        Schachbrettmirror.board[y][x] = new Rook.RookMirror(color, x, y);
                    } else if (piece instanceof Knight) {
                        Schachbrettmirror.board[y][x] = new Knight.KnightMirror(color, x, y);
                    } else if (piece instanceof Bishop) {
                        Schachbrettmirror.board[y][x] = new Bishop.BishopMirror(color, x, y);
                    } else if (piece instanceof Queen) {
                        Schachbrettmirror.board[y][x] = new Queen.QueenMirror(color, x, y);
                    } else if (piece instanceof King) {
                        Schachbrettmirror.board[y][x] = new King.KingMirror(color, x, y);
                    }
                } else {
                    Schachbrettmirror.board[y][x] = null;
                }
            }
        }

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (board[y][x] != null && board[y][x].getColor() == Figure.Color.White){
                    whitePieces.add(board[y][x]);
                }
                if (board[y][x] != null && board[y][x].getColor() == Figure.Color.Black){
                    blackPieces.add(board[y][x]);
                }


            }
        }
        enPassantForBlack = Schachbrett.enPassantForBlack;
        enPassantForWhite = Schachbrett.enPassantForWhite;
        if(Schachbrett.whitePassantPawn instanceof Pawn){
            whitePassantPawn = (Pawn) board[Schachbrett.whitePassantPawn.getYcord()][Schachbrett.whitePassantPawn.getXcord()];
        }
        if(Schachbrett.blackPassantPawn instanceof Pawn){
            blackPassantPawn = (Pawn) board[Schachbrett.blackPassantPawn.getYcord()][Schachbrett.blackPassantPawn.getXcord()];
        }


    }

    public static void resetEnPassant(Figure.Color color){
        if (color == Figure.Color.Black){
            if(whitePassantPawn != null){
                whitePassantPawn.isPresentable = false;
                whitePassantPawn = null;

            }
            enPassantForBlack = false;
        }
        if (color == Figure.Color.White){
            if(blackPassantPawn != null){
                blackPassantPawn.isPresentable = false;
                blackPassantPawn = null;

            }
            enPassantForWhite = false;
        }
    }

    public static boolean getEnPassant(Figure.Color color){
        if (color == Figure.Color.Black){
            return enPassantForBlack;
        }
        if (color == Figure.Color.White){
            return enPassantForWhite;
        }
        throw new RuntimeException("This should really never happen!");
    }

    public static void setEnPassant(Figure.Color color, Pawn target){
        if (color == Figure.Color.Black){
            blackPassantPawn = target;
            target.isPresentable = true;
            enPassantForWhite = true;
        }
        if (color == Figure.Color.White){
            whitePassantPawn = target;
            target.isPresentable = true;
            enPassantForBlack = true;
        }
    }

    public static void addPiece(Figure fig, Figure.Color color){
        if (color == Figure.Color.Black){
            blackPieces.add(fig);
        }
        if (color == Figure.Color.White){
            whitePieces.add(fig);
        }
    }
    public static boolean isAttacked(Figure.Color color, int checkX, int checkY){

        for(Figure Piece : (color == Figure.Color.White ? blackPieces : whitePieces)){
            if(Piece != null && Piece.isValidMoveGetter(checkX, checkY)){
                return true;
            }
        }
        return false;

    }
    public static boolean isInCheckAfterMove(Figure.Color color, int x, int y, int newX, int newY){

        if(board[y][x].movemirror(newX, newY)){
            int kingX = -1, kingY = -1;
            for (Figure Piece : (color == Figure.Color.White ? Schachbrettmirror.whitePieces : Schachbrettmirror.blackPieces)) {
                if (Piece instanceof King) {
                    kingX = Piece.getXcord();
                    kingY = Piece.getYcord();
                }
            }
            return isAttacked(color, kingX, kingY); //true when King is in Check
        }
        return false;
    }
    public static void clearMirror(){
        board = null;
        enPassantForWhite = false;
        enPassantForBlack = false;
        whitePassantPawn = null;
        blackPassantPawn = null;
        whitePieces.clear();
        blackPieces.clear();
    }
}
