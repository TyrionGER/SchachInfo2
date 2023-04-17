package Chess.Board.Board;

import Chess.Board.Figures.*;

import java.util.ArrayList;
import java.util.List;

public class Schachbrett {
    public static Figure[][] board;
    public static boolean enPassantForWhite = false;
    public static boolean enPassantForBlack = false;

    public static Pawn whitePassantPawn;
    public static Pawn blackPassantPawn;

    public static List<Figure> whitePieces = new ArrayList<>();
    public static List<Figure> blackPieces = new ArrayList<>();

    public static void initializeBoard(){
        board = new Figure[8][8];


        for(int i=0; i<8; i++) {
            board[1][i] = new Pawn(Figure.Color.White, i, 1);

            board[6][i] = new Pawn(Figure.Color.Black, i, 6);
            blackPieces.add(board[6][i]);
        }


        board[0][0] = new Rook(Figure.Color.White,0,0);
        board[0][1] = new Knight(Figure.Color.White,1,0);
        board[0][2] = new Bishop( Figure.Color.White,2,0);
        board[0][3] = new King( Figure.Color.White,3,0);
        board[0][4] = new Queen( Figure.Color.White,4,0);
        board[0][5] = new Bishop(Figure.Color.White,5,0);
        board[0][6] = new Knight(Figure.Color.White,6,0);
        board[0][7] = new Rook(Figure.Color.White,7,0);


        board[7][0] = new Rook(Figure.Color.Black,0,7);
        board[7][1] = new Knight(Figure.Color.Black,1,7);
        board[7][2] = new Bishop(Figure.Color.Black,2,7);
        board[7][3] = new King(Figure.Color.Black,3,7);
        board[7][4] = new Queen(Figure.Color.Black,4, 7);
        board[7][5] = new Bishop(Figure.Color.Black,5,7);
        board[7][6] = new Knight(Figure.Color.Black,6,7);
        board[7][7] = new Rook(Figure.Color.Black,7,7);
       for (int i = 0; i < 8; i++){
            whitePieces.add(board[0][i]);
            whitePieces.add(board[1][i]);
            blackPieces.add(board[6][i]);
            blackPieces.add(board[7][i]);
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
            if(Piece.isValidMoveGetter(checkX, checkY)){
                return true;
            }
        }
        return false;

    }
}
