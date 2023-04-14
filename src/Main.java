import java.util.Scanner;

public class Main {
    private static int whatmove = 1;
    private static int i = 1;
    private static int x = -1,y = -1,newX = -1, newY = -1;
    public static void main(String[] args) {
        Schachbrett Schachspiel = new Schachbrett();
        Schachfiguren.Schachfigur[][] Schach = Schachspiel.initializeSchachbrett();

        while(i == 1){
            Scanner scan = new Scanner(System.in);

            printSchachbrett(Schach);

            System.out.println("Bitte Start X Feld angeben: ");
            x = scan.nextInt();
            System.out.println("Bitte Start Y Feld angeben: ");
            y = scan.nextInt();
            System.out.println("Bitte Ziel X Feld angeben: ");
            newX = scan.nextInt();
            System.out.println("Bitte Ziel Y Feld angeben: ");
            newY = scan.nextInt();

            if((whatmove % 2) == 1){
                if(Schach[y][x] == null){
                    System.out.println("______Leeres Startfeld ausgewählt, neue Eingabe______");
                }else if(Schach[y][x].getColor() == Color.White){
                    if(Schach[y][x].isvalidmove(x, y, newX, newY, Schach)) {
                        Schach[y][x].move(x, y, newX, newY, Schach);
                        Schach[newY][newX] = Schach[y][x];
                        Schach[y][x].Promote(newY, newX, Schach);
                        Schach[y][x] = null;
                        whatmove++;
                    }
                    else{
                        System.out.println("______Zug nicht erlaubt_______");
                    }
                }
                else {
                    System.out.println("______Falsche Farbe oder Eingabe, Weiß war am Zug, neue Eingabe_______");
                }
            }
            else{
                if(Schach[y][x] == null){
                    System.out.println("______Leeres Startfeld ausgewählt, neue Eingabe______");
                }else if(Schach[y][x].getColor() == Color.Black){
                    if(Schach[y][x].isvalidmove(x,y,newX,newY,Schach) == true) {
                        Schach[y][x].move(x, y, newX, newY, Schach);
                        Schach[newY][newX] = Schach[y][x];
                        Schach[y][x].Promote(newY, newX, Schach);
                        Schach[y][x] = null;
                        whatmove++;
                    }
                    else{
                        System.out.println("______Zug nicht erlaubt_______");
                    }
                }
                else {
                    System.out.println("______Falsche Farbe oder Eingabe, Schwarz war am Zug, neue Eingabe_______");
                }
            }

        }
    }
    public static void printSchachbrett(Schachfiguren.Schachfigur[][] schachbrett) {
        System.out.println();
        System.out.printf("  ");
        System.out.printf("____________________________________________________________________________________________________________________________");
        System.out.println();
        for (int row = 7; row >= 0; row--) {
            System.out.printf("%-5s",String.valueOf(row) +" | ");
            for (int col = 0; col < 8; col++) {
                if (schachbrett[row][col] != null) {
                    System.out.printf("%-15s", schachbrett[row][col].toString());
                } else {
                    System.out.printf("%-15s", "----------");
                }
                if(col == 7){
                    System.out.printf("|");
                }
            }
            System.out.println();
        }
        System.out.printf("  |");
            System.out.printf("__________________________________________________________________________________________________________________________|");
        System.out.println();
        for(int i = 0; i <= 7; i++){
            System.out.printf("%-15s","          " + String.valueOf(i));
        }

        System.out.println();
    }
}

