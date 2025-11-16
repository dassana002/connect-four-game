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
        return this.boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for (int row = 0; row < NUM_OF_ROWS; row++) {
            if (pieces[col][row] == Piece.EMPTY) {
                return row;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        return findNextAvailableSpot(col) != -1;
    }

    @Override
    public boolean existLegalMoves() {
        for (int i = 0; i < NUM_OF_COLUMNS; i++) {
            for (int j = 0; j < NUM_OF_ROWS; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            if (pieces[col][i] == Piece.EMPTY) {
                pieces[col][i] = move;
                break;
            }
        }
    }

    @Override
    public Winner findWinner() {

        for (int i = 0; i < NUM_OF_COLUMNS; i++) {

            if (pieces[i][1] == pieces[i][2] && pieces[i][2] == pieces[i][3] && pieces[i][3] == pieces[i][4] && pieces[i][1] != Piece.EMPTY) {
                return new Winner(pieces[i][1],  i, 1, i, 4);
            }
            if ( pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2] && pieces[i][2] == pieces[i][3] && pieces[i][0] != Piece.EMPTY) {
                return new Winner( pieces[i][0], i, 0, i, 3);
            }
        }

        for (int j = 0; j < NUM_OF_ROWS; j++) {

            if (pieces[0][j] == pieces[1][j] && pieces[1][j] == pieces[2][j] && pieces[2][j] == pieces[3][j] && pieces[0][j] != Piece.EMPTY) {
                return new Winner(pieces[0][j], j,0, j, 3);
            }
            if (pieces[1][j] == pieces[2][j] && pieces[2][j] == pieces[3][j] && pieces[3][j] == pieces[4][j] && pieces[1][j] != Piece.EMPTY) {
                return new Winner(pieces[0][j], j,1, j, 4);
            }
            if (pieces[2][j] == pieces[3][j] && pieces[3][j] == pieces[4][j] && pieces[4][j] == pieces[5][j] && pieces[2][j] != Piece.EMPTY) {
                return new Winner(pieces[0][j], j,2, j, 5);
            }
        }
        return new Winner(Piece.EMPTY);
    }
}
