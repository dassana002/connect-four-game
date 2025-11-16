package lk.ijse.dep.service;

public class BoardImpl implements Board {
    private final Piece[][] pieces;
    private final BoardUI boardUI;
    
    public BoardImpl(BoardUI newboard){
        this.boardUI = newboard;

        pieces = new Piece[NUM_OF_COLUMNS][NUM_OF_ROWS];
        for(int i=0;i<NUM_OF_COLUMNS;i++){
            for(int j=0;j<NUM_OF_ROWS;j++){
                pieces[i][j]=Piece.EMPTY;
            }
        }
    }

    @Override
    public BoardUI getBoardUI() {
        return null;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        return 0;
    }

    @Override
    public boolean isLegalMove(int col) {
        return false;
    }

    @Override
    public boolean existLegalMoves() {
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {

    }

    @Override
    public Winner findWinner() {
        return null;
    }
}
