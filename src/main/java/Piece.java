package main.java;

public class Piece{

    private boolean alive = true;
    private boolean white;
    private boolean atStart = false; //used for checking for pawn's two-space movement or for castling
    private String type;
    private int[] coord = new int[2];

    Piece(boolean white, String type, int[] coord){
        this.white = white;
        this.type = type;
        this.coord = coord;

        if(type.equals("Pawn") || type.equals("Rook") || type.equals("King")){
            atStart = true;
        }
    }

    void kill() {
        alive = false;
    }

    void setCoord(int[] coord){
        this.coord = coord;
    }

    boolean isWhite() {
        return white;
    }

    boolean isAlive() {
        return alive;
    }

    public boolean atStart(){
        return atStart;
    }

    String getType(){
        return type;
    }

    int[] getCoord(){
        return coord;
    }
}
