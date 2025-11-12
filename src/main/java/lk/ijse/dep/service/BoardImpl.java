package lk.ijse.dep.service;

public class BoardImpl implements Board{
    Piece[][] piece =  new Piece[6][5];
    BoardUI boardUI;

    public BoardImpl(BoardUI boardUI){
        this.boardUI = boardUI;
    }

}
