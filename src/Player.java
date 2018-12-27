public class Player {
    private boolean color;
    private Piece king;
    private Piece queen;
    private Piece[] bishop = new Piece[2];
    private Piece[] rook = new Piece[2];
    private Piece[] knight = new Piece[2];
    private Piece[] pawn = new Piece[8];
    private boolean hasTurn;

    public Player(boolean color){
        this.color = color;
    }

    public boolean isWhite(){return color;}

    public void addPiece(Piece p){
        switch(p.getType()){
            case "King":
                king = p;
                break;
            case "Queen":
                queen = p;
                break;
            case "Rook":
                addToArray(rook, p);
                break;
            case "Bishop":
                addToArray(bishop, p);
                break;
            case "Knight":
                addToArray(knight, p);
                break;
            case "Pawn":
                addToArray(pawn, p);
                break;
            default:
                return;
        }
    }


    private void addToArray(Piece[] pieces, Piece p){
        for(int i = 0; i < pieces.length; i++)
            if(pieces[i] == null){
                pieces[i] = p;
                return;
            }
    }

    public Piece getKing(){ return king;}
    public Piece getQueen(){return queen;}
    public Piece[] getBishop(){ return bishop;}
    public Piece[] getRook(){ return rook;}
    public Piece[] getKnight(){ return knight;}
    public Piece[] getPawn(){ return pawn;}

    public void setTurn(){
        hasTurn = !hasTurn;
    }
}
