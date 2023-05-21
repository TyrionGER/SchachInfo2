package Chess.Board.Figures;

import Chess.Board.Board.Schachbrett;
import Chess.Board.Board.Schachbrettmirror;
import Chess.Board.Board.UI_UX;

import java.util.Scanner;

public class Pawn extends Figure{

    public Pawn(Color color, int x, int y) {
        super(color, x, y);
    }

    public boolean isPresentable;

    public boolean wasEnpassant;

    @Override
    protected boolean isValidMove(int targetX, int targetY) {
        wasEnpassant = false;
        if(targetX == Xcord && Schachbrett.board[targetY][targetX] != null){
            return false;
        }
        //Check for backwards movement
        if (color == Color.White && targetY <= Ycord) {
            return false;
        }
        if (color == Color.Black && targetY >= Ycord) {
            return false;
        }
        //Allow move from baseline
        if (Math.abs(Ycord - targetY) == 2 && Ycord == (color == Color.White ? 1 : 6) && Xcord == targetX) {
            isPresentable = true;
            return true;
        }
        //Check for wrong move distance
        if (Math.abs(Ycord - targetY) != 1) {
            return false;
        }
        //Allow straight move
        if (Xcord == targetX && Schachbrett.board[targetY][targetX] == null) {
            return true;
        }

        //Allow enemy capture
        if (Math.abs(Xcord - targetX) == 1 && Schachbrett.board[targetY][targetX] != null && Schachbrett.board[targetY][targetX].color != color) {
            return true;
        }

        if (Schachbrett.getEnPassant(color)){
            int passYCord = targetY - (color == Color.White ? 1 : -1);
            if (Schachbrett.board[passYCord][targetX] instanceof Pawn tp){
                wasEnpassant = tp.isPresentable;
                return tp.isPresentable;
            }
        }

        return false;
    }



    @Override
    public boolean Promote(int newX, int newY) {
        if (Schachbrett.board[newY][newX] == Schachbrett.board[7][newX] || Schachbrett.board[newY][newX] == Schachbrett.board[0][newX]) {
            Figure obj = null;
            while (obj == null){
                UI_UX UI = UI_UX.getInstance();
                int zahl = UI.openPromotionWindow();
                obj = switch (zahl) {
                    case 1 -> Schachbrett.board[newY][newX] = new Rook(getColor(), newX, newY);
                    case 2 -> Schachbrett.board[newY][newX] = new Queen(getColor(), newX, newY);
                    case 3 -> Schachbrett.board[newY][newX] = new Bishop(getColor(), newX, newY);
                    case 4 -> Schachbrett.board[newY][newX] = new Knight(getColor(), newX, newY);
                    default -> {
                        System.out.println("Ungueltige Eingabe.");
                        yield null;
                    }
                };
            }

            capture();
            Schachbrett.addPiece(obj, color);
            return true;
        }
        return false;
    }

    @Override
    public String getPiece() {
        return "Pawn";
    }

    @Override
    public boolean move(int newX, int newY) { // Wenn Pawn.move wird zuerst dieses Override, dann durch super.move die Basis move funktion ausgeführt, und wenn die true returned wird noch getestet ob der Zug 2 Felder weit geht, wenn ja wird enpassantmöglich für den nächsten Zug auf true gesetzt
        int moveDist = Math.abs((newY - Ycord));
        if (wasEnpassant){
            if (Schachbrett.board[Ycord - (color == Color.White ? 1 : -1)][Xcord] != null){
                Schachbrett.board[Ycord - (color == Color.White ? 1 : -1)][Xcord].capture();
            }
        }
        if (super.move(newX, newY)){
            if (moveDist == 2) {
                Schachbrett.setEnPassant(color, this);
            }

            return true;
        }
        return false;
    }
    @Override
    public boolean wasEnPassantgetter(){
        return wasEnpassant;
    }
    @Override
    public boolean isPresentablegetter(){
        return isPresentable;
    }

    public static class PawnMirror extends Pawn{
        public PawnMirror(Color color, int x, int y) {
            super(color, x, y);
        }

        @Override
        protected boolean isValidMove(int targetX, int targetY) {
            wasEnpassant = false;
            if(targetX == Xcord && Schachbrettmirror.board[targetY][targetX] != null){
                return false;
            }
            //Check for backwards movement
            if (color == Color.White && targetY <= Ycord) {
                return false;
            }
            if (color == Color.Black && targetY >= Ycord) {
                return false;
            }
            //Allow move from baseline
            if (Math.abs(Ycord - targetY) == 2 && Ycord == (color == Color.White ? 1 : 6) && Xcord == targetX) {
                isPresentable = true;
                return true;
            }
            //Check for wrong move distance
            if (Math.abs(Ycord - targetY) != 1) {
                return false;
            }
            //Allow straight move
            if (Xcord == targetX && Schachbrettmirror.board[targetY][targetX] == null) {
                return true;
            }

            //Allow enemy capture
            if (Math.abs(Xcord - targetX) == 1 && Schachbrettmirror.board[targetY][targetX] != null && Schachbrettmirror.board[targetY][targetX].color != color) {
                return true;
            }

            if (Schachbrettmirror.getEnPassant(color)){
                int passYCord = targetY - (color == Color.White ? 1 : -1);
                if (Schachbrettmirror.board[passYCord][targetX] instanceof Pawn tp){
                    wasEnpassant = tp.isPresentable;
                    return tp.isPresentable;
                }
            }

            return false;
        }

        @Override
        public boolean Promote(int newX, int newY) {
            if (Schachbrettmirror.board[newY][newX] == Schachbrettmirror.board[7][newX] || Schachbrettmirror.board[newY][newX] == Schachbrettmirror.board[0][newX]) {
                Figure obj = null;
                while (obj == null){
                    UI_UX UI = UI_UX.getInstance();
                    int zahl = UI.openPromotionWindow();

                    obj = switch (zahl) {
                        case 1 -> Schachbrettmirror.board[newY][newX] = new Rook(getColor(), newX, newY);
                        case 2 -> Schachbrettmirror.board[newY][newX] = new Queen(getColor(), newX, newY);
                        case 3 -> Schachbrettmirror.board[newY][newX] = new Bishop(getColor(), newX, newY);
                        case 4 -> Schachbrettmirror.board[newY][newX] = new Knight(getColor(), newX, newY);
                        default -> {
                            System.out.println("Ungueltige Eingabe.");
                            yield null;
                        }
                    };
                }

                capturemirror();
                Schachbrettmirror.addPiece(obj, color);
                return true;
            }
            return false;
        }

        @Override
        public String getPiece() {
            return "Pawn";
        }

        @Override
        public boolean move(int newX, int newY) {
            int moveDist = Math.abs((newY - Ycord));
            if (wasEnpassant){
                if (Schachbrettmirror.board[Ycord - (color == Color.White ? 1 : -1)][Xcord] != null){
                    Schachbrettmirror.board[Ycord - (color == Color.White ? 1 : -1)][Xcord].capturemirror();
                }
            }
            if (super.move(newX, newY)){
                if (moveDist == 2) {
                    Schachbrettmirror.setEnPassant(color, this);
                }

                return true;
            }
            return false;
        }
        @Override
        public void isPresentablesetter(boolean passantable){
            this.isPresentable = passantable;
        }

    }
}
