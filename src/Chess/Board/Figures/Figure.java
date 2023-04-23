package Chess.Board.Figures;

import Chess.Board.Board.*;

public abstract class Figure {  //Basisklasse Aller Figuren
    public enum Called{ //Enum Called ist dafür zuständig um zu Unterscheiden von welchem Brett aus eine Figur gerufen wird
        FromBoard,
        FromMirror
    }

    public enum Color { //Farbe wird initialisiert
        White,
        Black
    }

    public enum Type { //Figuren werden initialisiert
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

    public Figure(Color color, int x, int y) { //setze die farbe sowie die x und y Koordinaten
        this.color = color;
        this.Xcord = x;
        this.Ycord = y;
    }

    protected abstract boolean isValidMove(int newX, int newY); //abstrakte Klasse zur Überprüfung, ob ein Zug möglich ist

    public boolean move(int newX, int newY){ //Führt den Zug durch
        if (isValidMove(newX, newY)) { //bevor der zug ausgeführt werden kann, muss überprüft werden ob dieser gültig ist
            Schachbrett.board[Ycord][Xcord] = null;
            Xcord = newX;
            Ycord = newY; //Übergebe die neuen Koordinaten
            if (this instanceof Pawn tp && tp.wasEnpassant){ //Prüfe auf En passant, wenn ja, führe den zug aus
                if (Schachbrett.board[Ycord - (color == Color.White ? 1 : -1)][Xcord] != null){
                    Schachbrett.board[Ycord - (color == Color.White ? 1 : -1)][Xcord].capture(); //Schlage den Bauern
                    Schachbrett.board[Ycord - (color == Color.White ? 1 : -1)][Xcord] = null;
                }
            }
            Schachbrett.board[Ycord][Xcord] = this; //Übergebe die neue Position der Figur
            return true;
        }
        return false;
    }

    public boolean movemirror(int newX, int newY) { //Genau gleiche wie oben nur da es hier Simuliert wird
        if (isValidMove(newX, newY)) {
            int xHelper;
            int yHelper;
            if(Schachbrettmirror.board[newY][newX] != null){
                Schachbrettmirror.board[newY][newX].capturemirror();
            }
            xHelper = Xcord;
            yHelper = Ycord;
            Schachbrettmirror.board[Ycord][Xcord] = null;
            Xcord = newX;
            Ycord = newY;
            if (this instanceof Pawn tp && Schachbrett.board[yHelper][xHelper].wasEnPassantgetter()) {
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

    public void capture(){ //Funktion um die Figur Schlagen
        if (color == Color.White){
            Schachbrett.whitePieces.remove(this);
        }
        if (color == Color.Black){
            Schachbrett.blackPieces.remove(this);
        }
    }
    public void capturemirror() { //gleiche funktion wie capture nur für mirror.
        if (color == Color.White) {
            Schachbrettmirror.whitePieces.remove(this);
        }
        if (color == Color.Black) {
            Schachbrettmirror.blackPieces.remove(this);
        }
    }

    //region Getter
    public boolean wasEnPassantgetter(){
        return false;
    }
    public Color getColor() {
        return this.color;
    } //Gebe die farbe zurück

    public abstract String getPiece(); //Abstrakte Methode zur Ermittlung der Figur.
    @Override
    public String toString(){ //gibt die aktuelle Farbe sowie Figur zurück
        return this.color + this.getPiece();

    }

    public boolean isValidMoveGetter(int X, int Y){ //Da man isValidMove() nicht direkt aufrufen kann wird dieser über eine Getter Methode geliefert
        return isValidMove(X, Y);

    }
    public int getXcord(){ //gibt die X koordinate wieder
        return Xcord;
    }
    public int getYcord(){ //gibt die Y Koordinate wieder
        return Ycord;
    }

    public boolean isPresentablegetter(){
        return false;
    }

    public void isPresentablesetter(boolean passantable){
        return;
    }
    //endregion


}

