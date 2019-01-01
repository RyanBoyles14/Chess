package main.java;

public interface IPiece {

    void kill();

    void setCoord(int[] coord);

    //The valid moveTypes are horizontal, vertical, diagonal, and l-shape
    boolean validMove(String moveType, int range, boolean forward);

    boolean isWhite();

    boolean isAlive();

    int[] getCoord();

    //return whether the piece is at its starting position
    boolean getStart();
}
