package main.java;

public class Rook implements IPiece{

    private boolean alive = true;
    private boolean atStart = true; //used for checking for pawn's two-space movement or for castling
    private int[] coord;
    private boolean white;

    Rook(boolean white, int[] coord){
        this.white = white;
        this.coord = coord;
    }

    public void kill() {
        alive = false;
    }

    public void setCoord(int[] coord){
        this.coord = coord;
        atStart = false;
    }

    @Override
    //Strictly horizontal/vertical, range > 0, can castle with the King
    public boolean validMove(String moveType, int range, boolean forward) {
        return false;
    }

    public boolean isWhite() {
        return white;
    }

    public boolean isAlive() {
        return alive;
    }

    public int[] getCoord(){ return coord; }

    @Override
    public boolean getStart() {
        return atStart;
    }
}
