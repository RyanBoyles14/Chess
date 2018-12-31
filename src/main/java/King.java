package main.java;

public class King implements IPiece{

    private boolean alive = true;
    private boolean atStart = true; //used for checking for pawn's two-space movement or for castling
    private int[] coord;
    private boolean white;

    King(boolean white, int[] coord){
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

    public boolean move() {

        return false;
    }

    public boolean isWhite() {
        return white;
    }

    public boolean isAlive() {
        return alive;
    }

    public int[] getCoord(){ return coord; }
}
