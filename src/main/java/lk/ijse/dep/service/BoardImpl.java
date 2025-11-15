package lk.ijse.dep.service;

public class BoardImpl {
    Piece[][] piece =  new Piece[6][5];
    BoardUI boardUI;

    public BoardImpl(BoardUI boardUI){
        this.boardUI = boardUI;
    }

}
