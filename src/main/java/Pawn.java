package main.java;

public class Pawn implements IPiece{

    private boolean alive = true;
    private boolean atStart = true; //used for checking for pawn's two-space movement
    private boolean doubleStep = false; //check if the pawn's last move was a double-step. Important for checking en passant captures
    private int[] coord;
    private boolean white;
    private String type = "Pawn";

    Pawn(boolean white, int[] coord){
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
    //range = 1 or 2, depending on position. Diagonal movement assuming a capture. Only forward movement
    public boolean validMove(String moveType, int range, boolean forward) throws MovementException {
        if(forward && ((range == 2 && atStart) || range == 1) && moveType.equals("vertical"))
            return false;
        else if(forward && range == 1 && moveType.equals("diagonal"))
            return true;
        else
            throw new MovementException("That is not a valid move for a Pawn.");
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
