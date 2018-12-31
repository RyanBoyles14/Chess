package main.java;

public class Knight implements IPiece{

    private boolean alive = true;
    private int[] coord;
    private boolean white;

    Knight(boolean white, int[] coord){
        this.white = white;
        this.coord = coord;
    }

    public void kill() {
        alive = false;
    }

    public void setCoord(int[] coord){
        this.coord = coord;
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
