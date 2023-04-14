public class Schachbrett {
    public Schachfiguren.Schachfigur[][] initializeSchachbrett(){
        Schachfiguren schachfiguren = new Schachfiguren();
        Schachfiguren.Schachfigur[][] schachbrett = new Schachfiguren.Schachfigur[8][8];
        //          Y   X
        schachbrett[0][0] = schachfiguren.new Schachfigur(Pieces.Rook, Color.White,0,0);
        schachbrett[0][1] = schachfiguren.new Schachfigur(Pieces.Knight, Color.White,1,0);
        schachbrett[0][2] = schachfiguren.new Schachfigur(Pieces.Bishop, Color.White,2,0);
        schachbrett[0][3] = schachfiguren.new Schachfigur(Pieces.King, Color.White,3,0);
        schachbrett[0][4] = schachfiguren.new Schachfigur(Pieces.Queen, Color.White,4,0);
        schachbrett[0][5] = schachfiguren.new Schachfigur(Pieces.Bishop, Color.White,5,0);
        schachbrett[0][6] = schachfiguren.new Schachfigur(Pieces.Knight, Color.White,6,0);
        schachbrett[0][7] = schachfiguren.new Schachfigur(Pieces.Rook, Color.White,7,0);
        schachbrett[1][0] = schachfiguren.new Schachfigur(Pieces.Pawn, Color.White,0, 1);
        schachbrett[1][1] = schachfiguren.new Schachfigur(Pieces.Pawn, Color.White,1,1);
        schachbrett[1][2] = schachfiguren.new Schachfigur(Pieces.Pawn, Color.White,2,1);
        schachbrett[1][3] = schachfiguren.new Schachfigur(Pieces.Pawn, Color.White,3,1);
        schachbrett[1][4] = schachfiguren.new Schachfigur(Pieces.Pawn, Color.White,4,1);
        schachbrett[1][5] = schachfiguren.new Schachfigur(Pieces.Pawn, Color.White,5,1);
        schachbrett[1][6] = schachfiguren.new Schachfigur(Pieces.Pawn, Color.White,6,1);
        schachbrett[1][7] = schachfiguren.new Schachfigur(Pieces.Pawn, Color.White,7,1);
        schachbrett[7][0] = schachfiguren.new Schachfigur(Pieces.Rook, Color.Black,0,7);
        schachbrett[7][1] = schachfiguren.new Schachfigur(Pieces.Knight, Color.Black,1,7);
        schachbrett[7][2] = schachfiguren.new Schachfigur(Pieces.Bishop, Color.Black,2,7);
        schachbrett[7][3] = schachfiguren.new Schachfigur(Pieces.King, Color.Black,3,7);
        schachbrett[7][4] = schachfiguren.new Schachfigur(Pieces.Queen, Color.Black,4, 7);
        schachbrett[7][5] = schachfiguren.new Schachfigur(Pieces.Bishop, Color.Black,5,7);
        schachbrett[7][6] = schachfiguren.new Schachfigur(Pieces.Knight, Color.Black,6,7);
        schachbrett[7][7] = schachfiguren.new Schachfigur(Pieces.Rook, Color.Black,7,7);
        schachbrett[6][0] = schachfiguren.new Schachfigur(Pieces.Pawn, Color.Black,0,6);
        schachbrett[6][1] = schachfiguren.new Schachfigur(Pieces.Pawn, Color.Black,1,6);
        schachbrett[6][2] = schachfiguren.new Schachfigur(Pieces.Pawn, Color.Black,2,6);
        schachbrett[6][3] = schachfiguren.new Schachfigur(Pieces.Pawn, Color.Black,3,6);
        schachbrett[6][4] = schachfiguren.new Schachfigur(Pieces.Pawn, Color.Black,4,6);
        schachbrett[6][5] = schachfiguren.new Schachfigur(Pieces.Pawn, Color.Black,5,6);
        schachbrett[6][6] = schachfiguren.new Schachfigur(Pieces.Pawn, Color.Black,6,6);
        schachbrett[6][7] = schachfiguren.new Schachfigur(Pieces.Pawn, Color.Black,7,6);
        return schachbrett;
    }
}
