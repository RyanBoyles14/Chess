package main.java;

public class Piece implements IPiece{

    private boolean alive = true;
    private boolean atStart = true; //used for checking for pawn's two-space movement or for castling
    private int[] coord = new int[2];
    private boolean white;
    private String type;

    Piece(boolean white, String type, int[] coord){
        this.white = white;
        this.type = type;
        this.coord = coord;
    }

    public void kill() {
        alive = false;
    }

    public void setCoord(int[] coord){
        this.coord = coord;
        atStart = false;
    }

    public void move() {

    }

    public boolean isWhite() {
        return white;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean atStart(){
        return atStart;
    }

    public String getType(){ return type; }

    public int[] getCoord(){ return coord; }
}
