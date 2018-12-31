package main.java;

public class Player {
    private boolean color;
    private IPiece king;
    private IPiece queen;
    private IPiece[] bishop = new Bishop[2];
    private IPiece[] rook = new Rook[2];
    private IPiece[] knight = new Knight[2];
    private IPiece[] pawn = new Pawn[8];
    private boolean hasTurn;

    Player(boolean color){
        this.color = color;
    }

    boolean isWhite(){return color;}

    void addPiece(IPiece p){
        switch(p.getClass().toString()){
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
        }
    }


    private void addToArray(IPiece[] pieces, IPiece p){
        for(int i = 0; i < pieces.length; i++)
            if(pieces[i] == null){
                pieces[i] = p;
                return;
            }
    }

    IPiece getKing(){ return king;}
    IPiece getQueen(){return queen;}
    IPiece[] getBishop(){ return bishop;}
    IPiece[] getRook(){ return rook;}
    IPiece[] getKnight(){ return knight;}
    IPiece[] getPawn(){ return pawn;}

    public void setTurn(){
        hasTurn = !hasTurn;
    }
}
