package main.java;

public class King implements IPiece{

    private boolean alive = true;
    private boolean atStart = true; //used for checking for pawn's two-space movement or for castling
    private int[] coord;
    private boolean white;
    private String type = "King";

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

    @Override
    //Must support castling when the King and Rook have not moved. Otherwise, range = 1, any movement but l-shape
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
