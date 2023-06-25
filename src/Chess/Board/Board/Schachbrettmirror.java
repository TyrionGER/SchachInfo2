package Chess.Board.Board;

import Chess.Board.Figures.*;

import java.util.ArrayList;
import java.util.List;

public class Schachbrettmirror extends Schachbrett {
    public static Figure[][] board;
    public static boolean enPassantForWhite = false;
    public static boolean enPassantForBlack = false;

    public static Pawn whitePassantPawn;
    public static Pawn blackPassantPawn;

    public static List<Figure> whitePieces = new ArrayList<>();
    public static List<Figure> blackPieces = new ArrayList<>();

    public static boolean isCheckMate = false;

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
            Schachbrettmirror.board[Schachbrett.whitePassantPawn.getYcord()][Schachbrett.whitePassantPawn.getXcord()].isPresentablesetter(Schachbrett.whitePassantPawn.isPresentablegetter());
        }
        if(Schachbrett.blackPassantPawn instanceof Pawn){
            blackPassantPawn = (Pawn) board[Schachbrett.blackPassantPawn.getYcord()][Schachbrett.blackPassantPawn.getXcord()];
            Schachbrettmirror.board[Schachbrett.blackPassantPawn.getYcord()][Schachbrett.blackPassantPawn.getXcord()].isPresentablesetter(Schachbrett.blackPassantPawn.isPresentablegetter());
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
        //Test ob der König der durchgegebenen Farbe im Schach steht nachdem der Zug gemacht wurde

        if(board[y][x] != null && color == board[y][x].getColor() && board[y][x].movemirror(newX, newY)){
            int kingX = -1, kingY = -1;
            for (Figure Piece : (color == Figure.Color.White ? Schachbrettmirror.whitePieces : Schachbrettmirror.blackPieces)) {
                if (Piece instanceof King) {
                    kingX = Piece.getXcord();
                    kingY = Piece.getYcord();
                }
            }
            return isAttacked(color, kingX, kingY); //true when King is in Check.
        }
        return true;
    }


    public static boolean isMatt(Figure.Color color, int x, int y) {
        clearMirror();
        initializeMirror();
        //Check if there is a move for King where King isnt in Check
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                clearMirror();
                initializeMirror();
                if (dx == 0 && dy == 0) {
                    continue; // King didnt move
                }
                int newX = x + dx;
                int newY = y + dy;
                if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
                    continue; // Out of bounds --> next iteration
                }
                if (isInCheckAfterMove(color == Figure.Color.White ? Figure.Color.Black : Figure.Color.White, x, y, newX, newY)) {
                    continue; // King is in check --> next iteration
                }
                // there is a valid move where your king isnt in check
                return false;
            }

        }
        //go through all pieces and check all squares if they could Protect the King

        clearMirror();
        initializeMirror();

        for (Figure Piece : (color == Figure.Color.Black ? Schachbrett.whitePieces : Schachbrett.blackPieces)) {//Test ob die eine Figur der angegriffenen Farbe ein Feld auf dem Schachbrett hat auf das es Springen kann sodass der eigene König nicht mehr im Schach steht
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    clearMirror();
                    initializeMirror();

                    if (!isInCheckAfterMove(Piece.getColor(), Piece.getXcord(), Piece.getYcord(), k, j)) {
                        // there is a valid move to protect your King
                        return false;
                    }
                }
            }
        }
        //there is no valid move to protect your King

        isCheckMate = true;
        return true; //returns true when mate
    }


    public static boolean Patt(Figure.Color color, int x, int y){


        for(int i = 0 ;  ; i++){ // test ob eine Figur der eingegebenen Farbe irgendeinen legalen Zug hat
            if(i >= (color == Figure.Color.White ? Schachbrettmirror.whitePieces.size() : Schachbrettmirror.blackPieces.size())){
                break;
            }
            Figure Piece = (color == Figure.Color.White ? Schachbrettmirror.whitePieces.get(i) : Schachbrettmirror.blackPieces.get(i));

            for(int j = 0; j <= 7; j++){ //y wert
                for(int k = 0; k <= 7; k++){ //x wert
                    clearMirror();
                    initializeMirror();
                    if(Piece.isValidMoveGetter(k, j)&& !isInCheckAfterMove(color, Piece.getXcord(), Piece.getYcord(), k, j)){
                        return false;
                    }

                }
            }
        }
        return true;
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