package Chess.Board.Board;

import java.util.Scanner;

import Chess.Board.Figures.*;

public class Main extends GameloopController{
    public static void main(String[] args) {

        int eingabe = 0;
        System.out.println("Glueckwunsch " + getCurrentColor() + " hat die Partie Gewonnen!");
        System.out.println("Menue:");
        System.out.println("1). Revanche");
        System.out.println("2). Applikation Beenden");
        System.out.println("3). Achievements");
        switch (eingabe){
            case 1:
                //Schachfeld neu initialisieren
                //die folgenden 2 zeilen sorgen dafür das die Konsole gecleared wird
                System.out.print("\033[H\033[2J");
                System.out.flush();
                GameloopController gameloop = new GameloopController();
                gameloop.Startchess();
            case 2:

            case 3:
                int reset = 0;
                System.out.println("Hier sind Alle Achievements:");
                System.out.println("1). Achivement XY [" + "]"); // if (ach_eins)  string_eins_set = X else " "
                System.out.println("2). Achivement XY [" + "]");
                System.out.println("3). Achivement XY [" + "]");
               /*System.out.println("99). Achivements zuruecksetzen "); OPTIONAL
                 switch (reset){
                    case 99:
                        //setze alle Achivement variablen auf 0
                }*/
            default:
                System.out.println("Unguelige Eingabe");




    }
}

