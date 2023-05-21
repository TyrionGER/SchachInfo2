package Chess.Board.Board;

import java.util.Scanner;
import java.util.TimerTask;

import Chess.Board.Figures.*;

public class Main extends UI_UX{
    static GameloopController gameloop = new GameloopController();

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int eingabe = 0;
        System.out.println("Menue:");
        System.out.println("1). Neues Schachspiel");
        System.out.println("2). Applikation Beenden");
        System.out.println("3). Achievements");
        eingabe = scan.nextInt();
        
        switch (eingabe) {
            case 1:
                //Schachfeld neu initialisieren
                //die folgenden 2 zeilen sorgen dafür das die Konsole gecleared wird
                System.out.print("\033[H\033[2J");
                System.out.flush();
                chessTimer chessTimer = new chessTimer(900);
                chessTimer.start();
                gameloop.Startchess();

            case 2:
                break;
            case 3:
                int reset = 0;
                System.out.println("Hier sind Alle Achievements:");
                System.out.println("1). Achivement XY [" + "]"); // if (ach_eins)  string_eins_set = X else " ".
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
}

