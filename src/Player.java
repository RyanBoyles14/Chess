public class Player {
    String color;
    Piece king;
    boolean hasTurn;

    public Player(String color){
        this.color = color;
    }

    public void setKing(Piece king){
        this.king = king;
    }

    public void setTurn(){
        hasTurn = !hasTurn;
    }
}
