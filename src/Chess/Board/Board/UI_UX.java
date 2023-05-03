package Chess.Board.Board;
import javax.imageio.ImageIO;
import javax.swing.*;// Importiert alle Klassen und Pakete aus javax.swing
import java.awt.*;// Importiert alle Klassen und Pakete aus java.awt
import java.io.File;
import java.io.IOException;


public class UI_UX extends JPanel{


    private static final int QUADRAT_GROESSE = 50; // Definition der Konstante für die Größe der Quadrate
    private static final int BREITE = 8 * QUADRAT_GROESSE; // Definition der Konstante für die Breite des Schachbretts
    private static final int HOEHE = BREITE; // Definition der Konstante für die Höhe des Schachbretts
    private static final Color BRAUN = new Color(139, 69, 19); // Definition der Konstante für die Farbe Braun
    private static final Color BEIGE = new Color(255, 228, 181); // Definition der Konstante für die Farbe BEIGE

    public UI_UX(){
        setPreferredSize(new Dimension(BREITE, HOEHE));// Setzt die bevorzugte Größe des Panels
    }

    public void paintComponent(Graphics g) { // Überschreiben der paintComponent-Methode
        super.paintComponent(g); // Aufruf der paintComponent-Methode der Elternklasse (JPanel)
        for (int i = 0; i < 8; i++) { // Schleife für die Anzahl der Zeilen
            for (int j = 0; j < 8; j++) { // Schleife für die Anzahl der Spalten
                Color color = (i + j) % 2 == 0 ? BRAUN : BEIGE;
                g.setColor(color);
                g.fillRect(i * QUADRAT_GROESSE, j * QUADRAT_GROESSE, QUADRAT_GROESSE, QUADRAT_GROESSE);

                if (j == 1) {
                    Image image = null;
                    try {
                        image = ImageIO.read(new File("Imgfiles/Figuren/whitePawn.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    g.drawImage(image, i * QUADRAT_GROESSE, j * QUADRAT_GROESSE, null);
                } else if (j == 6) {
                    Image image = null;
                    try {
                        image = ImageIO.read(new File("Imgfiles/Figuren/blackPawn.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    g.drawImage(image, i * QUADRAT_GROESSE, j * QUADRAT_GROESSE, null);
                }
            }
        }
    }

    public static void main(String[] args) { // Hauptmethode des Programms
        JFrame frame = new JFrame("UI.Schachbrett"); // Erstellen eines neuen JFrame mit dem Titel "UI. Schachbrett"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Setzen der Schließen-Aktion des Fensters
        Schachbrett board = new Schachbrett(); // Erstellen eines neuen Schachbretts
        frame.add(board); // Hinzufügen des Schachbretts zum JFrame
        frame.pack();// Setzt die Größe des Fensters basierend auf der bevorzugten Größe des Panels
        frame.setResizable(false);// Deaktiviert das Ändern der Größe des Fensters
        frame.setVisible(true); // Anzeigen des JFrame auf dem Bildschirm
    }
}
