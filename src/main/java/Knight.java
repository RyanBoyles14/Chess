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

    @Override
    //L-shaped movements, only piece that can jump over all other pieces
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
        return false;
    }
}
