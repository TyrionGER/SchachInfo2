import java.util.Scanner;

public class Schachfiguren {
    public class Schachfigur {
        private Pieces piece;
        private final Color color;
        private int Xcord;
        private int Ycord;
        private boolean enPassant = false;
        private static boolean enPassantpossiblelastroundwhite = false;
        private static boolean enPassantpossiblelastroundblack = false;
        private static int enPassantpawnwhiteX = -1;
        private static int enPassantpawnwhiteY = -1;
        private static int enPassantpawnblackX = -1;
        private static int enPassantpawnblackY = -1;


        public Schachfigur(Pieces name, Color color, int x, int y) {
            this.piece = name;
            this.color = color;
            this.Xcord = x;
            this.Ycord = y;
        }

        public String toString() {
            return color.toString() + " " + piece.toString();
        }

        public Color getColor() {
            return this.color;
        }

        public Pieces getPiece() {
            return this.piece;
        }

        public boolean isvalidmove(int oldX, int oldY, int newX, int newY, Schachfigur[][] schachbrett) {
            if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
                return false;
            }
            switch (piece) {
                case King:
                    //test ob Zug 1 Feld oder groeßer
                    if (Math.abs(oldX - newX) > 1 || Math.abs(oldY - newY) > 1) {
                        return false;
                    }
                    //test ob Zielfeld frei ist
                    if (schachbrett[newY][newX] != null) {

                        return false;
                    }
                    return true;

                case Pawn:
                    switch (color) {

                        case White:
                            //test ob nach vorne bewegt wird
                            if (oldY >= newY) {
                                return false;
                            }
                            //Test ob Bauer nach vorne geht und das Feld frei ist
                            if(oldX == newX && schachbrett[newX][newY] != null){
                                return false;
                            }

                            //test ob Bauer mehr als einen Schritt macht und 2 nach vorne darf
                            if (Math.abs(oldY - newY) >= 2) {
                                if (Math.abs(oldY - newY) == 2 && oldY == 1) {
                                    return true;
                                }
                                return false;
                            }
                            //test ob Bauer diagonal schlagen darf
                            if (oldX != newX) {
                                if (schachbrett[newY][newX] != null) {
                                    return true;
                                }
                                //enPassant test
                                if (newX == enPassantpawnblackX && newY == enPassantpawnblackY && enPassantpossiblelastroundblack) {
                                    //Muss geändert werden wenn Isvalidmove zum anzeigen der Züge verwendet werden soll
                                    enPassant = true;
                                    return true;
                                }

                                return false;
                            }
                            return true;
                        case Black:
                            //test ob nach vorne bewegt wird
                            if (oldY <= newY) {
                                return false;
                            }
                            //Test ob Bauer nach vorne geht und das Feld frei ist
                            if(oldX == newX && schachbrett[newX][newY] != null){
                                return false;
                            }

                            //test ob Bauer mehr als einen Schritt macht und 2 nach vorne darf
                            if (Math.abs(oldY - newY) >= 2) {
                                if (Math.abs(oldY - newY) == 2 && oldY == 6) {
                                    return true;
                                }
                                return false;
                            }
                            //test ob Bauer diagonal schlagen darf
                            if (oldX != newX) {
                                if (schachbrett[newY][newX] != null) {
                                    return true;

                                }
                                //enPassant test
                                if (newX == enPassantpawnwhiteX && newY == enPassantpawnwhiteY && enPassantpossiblelastroundwhite) {
                                    //Muss geändert werden wenn Isvalidmove zum anzeigen der Züge verwendet werden soll
                                    enPassant = true;
                                    return true;
                                }
                                return false;
                            }
                            return true;
                    }

                case Rook:
                    //test ob Wagerecht oder senkrecht
                    if (oldX != newX && oldY != newY) {
                        return false;
                    }
                    //Test ob zwischen Start und endposition Figuren sind
                    if (oldX == newX || oldY == newY) {
                        if (oldX < newX) { //Bewegung von links nach rechts
                            for (int i = oldX + 1; i < newX; i++) {
                                if (schachbrett[newY][i] != null) {
                                    return false;
                                }
                            }
                        } else if (oldX > newX) { //Bewegung von rechts nach links
                            for (int i = oldX - 1; i > newX; i--) {
                                if (schachbrett[newY][i] != null) {
                                    return false;
                                }
                            }

                        }
                        if (oldY < newY) { //bewegung von unten nach oben
                            for (int i = oldY + 1; i < newY; i++) {
                                if (schachbrett[i][newX] != null) {
                                    return false;
                                }

                            }
                        } else if (oldY > newY) {//bewegung von oben nach unten
                            for (int i = oldY - 1; i > newY; i--) {
                                if (schachbrett[i][newX] != null) {
                                    return false;
                                }
                            }

                        }


                    }
                    return true;

                case Queen:
                    //test ob Wagerecht, Senkrecht
                    if (oldX != newX && oldY != newY) {
                        //test ob Diagonal
                        if (Math.abs(newX - oldX) != Math.abs(newY - oldY)) {
                            return false;
                        } else if (newX - oldX == newY - oldY || newX - oldX == -(newY - oldY)) {
                            int xDir = (newX - oldX) > 0 ? 1 : -1;
                            int yDir = (newY - oldY) > 0 ? 1 : -1;

                            int x = oldX + xDir;
                            int y = oldY + yDir;

                            while (x != newX && y != newY) {
                                if (schachbrett[y][x] != null) {
                                    return false;
                                }

                                x += xDir;
                                y += yDir;
                            }
                        }
                    }

                    //Test ob zwischen Start und endposition Figuren sind
                    if (oldX == newX || oldY == newY) {
                        if (oldX < newX) { //Bewegung von links nach rechts
                            for (int i = oldX + 1; i < newX; i++) {
                                if (schachbrett[newY][i] != null) {
                                    return false;
                                }
                            }
                        } else if (oldX > newX) { //Bewegung von rechts nach links
                            for (int i = oldX - 1; i > newX; i--) {
                                if (schachbrett[newY][i] != null) {
                                    return false;
                                }
                            }

                        }
                        if (oldY < newY) { //bewegung von unten nach oben
                            for (int i = oldY + 1; i < newY; i++) {
                                if (schachbrett[i][newX] != null) {
                                    return false;
                                }

                            }
                        } else if (oldY > newY) {//bewegung von oben nach unten
                            for (int i = oldY - 1; i > newY; i--) {
                                if (schachbrett[i][newX] != null) {
                                    return false;
                                }
                            }

                        }


                    }
                    return true;


                case Bishop:
                    if (Math.abs(newX - oldX) != Math.abs(newY - oldY)) {
                        return false;
                    } else if (newX - oldX == newY - oldY || newX - oldX == -(newY - oldY)) {
                        int xDir = (newX - oldX) > 0 ? 1 : -1;
                        int yDir = (newY - oldY) > 0 ? 1 : -1;

                        int x = oldX + xDir;
                        int y = oldY + yDir;

                        while (x != newX && y != newY) {
                            if (schachbrett[y][x] != null) {
                                return false;
                            }

                            x += xDir;
                            y += yDir;
                        }
                    }
                    return true;

                case Knight:
                    int deltaX = Math.abs(newX - oldX);
                    int deltaY = Math.abs(newY - oldY);
                    if ((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2)) {
                        //Test ob Zielfeld frei ist
                        if (schachbrett[newX][newY] != null) {
                            return false;
                        }
                        return true;
                    }
                    return false;


            }

            return false;
        }

        public boolean move(int oldX, int oldY, int newX, int newY, Schachfigur[][] schachbrett) {
            switch (piece) {
                case King, Rook, Queen, Bishop, Knight:
                    if (isvalidmove(oldX, oldY, newX, newY, schachbrett)) {
                        Xcord = newX;
                        Ycord = newY;
                        return true;
                    }
                case Pawn:
                    if (isvalidmove(oldX, oldY, newX, newY, schachbrett)) {
                        if (Math.abs(oldY - newY) >= 2) {
                            if (Math.abs(oldY - newY) == 2 && (oldY == 1 || oldY == 6)) {
                                switch(color){
                                    case White:
                                        enPassantpossiblelastroundwhite = true;
                                        enPassantpawnwhiteY = newY - 1;
                                        enPassantpawnwhiteX = newX;
                                    case Black:
                                        enPassantpossiblelastroundblack = true;
                                        enPassantpawnblackY = newY + 1;
                                        enPassantpawnblackX = newX;

                                }
                            }
                        }
                        if(enPassant){
                            switch(color) {
                                case White:
                                    schachbrett[enPassantpawnblackY - 1][enPassantpawnblackX] = null;

                                case Black:
                                    schachbrett[enPassantpawnwhiteY + 1][enPassantpawnwhiteX] = null;
                            }
                        }
                        Xcord = newX;
                        Ycord = newY;
                        enPassant = false;

                        return true;
                    }

                    return false;
            }
            return false;
        }

        public void Promote(int newY, int newX, Schachfigur[][] schachbrett) {
            if (piece == Pieces.Pawn && ((schachbrett[newY][newX] == schachbrett[7][newX]) || (schachbrett[newY][newX] == schachbrett[0][newX]))) {
                System.out.println("Welche Figur wollen sie ?");
                System.out.println("Rook = 1, Queen = 2, Bishop = 3, Knight = 4");

                Scanner scan = new Scanner(System.in);
                int zahl = scan.nextInt();

                switch (zahl) {
                    case 1:
                        schachbrett[newY][newX] = new Schachfigur(Pieces.Rook, getColor(), newY, newX);
                        break;
                    case 2:
                        schachbrett[newY][newX] = new Schachfigur(Pieces.Queen, getColor(), newY, newX);
                        break;
                    case 3:
                        schachbrett[newY][newX] = new Schachfigur(Pieces.Bishop, getColor(), newY, newX);
                        break;
                    case 4:
                        schachbrett[newY][newX] = new Schachfigur(Pieces.Knight, getColor(), newY, newX);
                        break;
                    default:
                        System.out.println("Ungueltige Eingabe.");
                }
            }
        }
    }
}