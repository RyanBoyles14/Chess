package main.java;

public class Bishop implements IPiece{

    private boolean alive = true;
    private int[] coord;
    private boolean white;
    private String type = "Bishop";

    Bishop(boolean white, int[] coord){
        this.white = white;
        this.coord = coord;
    }

    public void kill() {
        alive = false;
    }

    public void setCoord(int[] coord){
        this.coord = coord;
    }

    @Override
    //Only diagonal, range > 0
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
    public String getType() {
        return type;
    }

    @Override
    public boolean getStart() {
        return false;
    }
}
