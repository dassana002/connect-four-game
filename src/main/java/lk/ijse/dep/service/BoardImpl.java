package lk.ijse.dep.service;

public class BoardImpl implements BoardUI{
    Piece[][] piece =  new Piece[6][5];
    BoardUI boardUI;

    BoardImpl(BoardUI boardUI){
        this.boardUI = boardUI;
    }

}
