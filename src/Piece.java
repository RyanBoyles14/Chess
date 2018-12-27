public class Piece{

    private boolean alive = true;
    private boolean white;
    private boolean atStart = false; //used for checking for pawn's two-space movement or for castling
    private String type;
    private int[] coord = new int[2];

    public Piece(boolean white, String type, int[] coord){
        this.white = white;
        this.type = type;
        this.coord = coord;

        if(type.equals("Pawn") || type.equals("Rook") || type.equals("King")){
            atStart = true;
        }
    }

    public void kill() {
        alive = false;
    }

    public void setCoord(int[] coord){
        this.coord = coord;
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

    public String getType(){
        return type;
    }

    public int[] getCoord(){
        return coord;
    }
}
