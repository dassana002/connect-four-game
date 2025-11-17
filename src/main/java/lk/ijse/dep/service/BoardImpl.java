package lk.ijse.dep.service;

public class BoardImpl implements Board {
    private final Piece[][] pieces;
    private final BoardUI boardUI;
    
    public BoardImpl(BoardUI newboard){
        this.boardUI = newboard;

        this.pieces = new Piece[NUM_OF_COLUMNS][NUM_OF_ROWS];

        for(int i=0;i<NUM_OF_COLUMNS;i++){
            for(int j=0;j<NUM_OF_ROWS;j++){
                pieces[i][j]=Piece.EMPTY;
            }
        }
    }

    /// Used to communicate with the UI layer to update the board visually.
    @Override
    public BoardUI getBoardUI() {
        return this.boardUI;
    }

    /// Finds the first empty row position in the specified column.
    @Override
    public int findNextAvailableSpot(int col) {
        for (int row = 0; row < NUM_OF_ROWS; row++) {
            if (pieces[col][row] == Piece.EMPTY) {
                return row;
            }
        }
        return -1;
    }

    /// Checks whether the specified column is valid.
    @Override
    public boolean isLegalMove(int col) {
        if (col < 0 || col >= NUM_OF_COLUMNS) return false;
        return findNextAvailableSpot(col) != -1;
    }

    /// Checks whether any legal empty spots are still available in the game.
    /// Checks if the game can continue.
    @Override
    public boolean existLegalMoves() {
        for (int col = 0; col < NUM_OF_COLUMNS; col++) {
            if (isLegalMove(col)) {
                return false;
            }
        }
        return true;
    }

    /// Add a new Piece
    @Override
    public void updateMove(int col, Piece move) {
        int row = findNextAvailableSpot(col);
        pieces[col][row] = move;
    }

    /// Determines whether the game has been won and identifies the winning condition.
    @Override
    public Winner findWinner() {

        for (int i = 0; i < NUM_OF_COLUMNS; i++) {

            if (pieces[i][1] == pieces[i][2] &&
                    pieces[i][2] == pieces[i][3] &&
                    pieces[i][3] == pieces[i][4] &&
                    pieces[i][1] != Piece.EMPTY)
                    return new Winner(pieces[i][1],  i, 1, i, 4);

            if ( pieces[i][0] == pieces[i][1] &&
                    pieces[i][1] == pieces[i][2] &&
                    pieces[i][2] == pieces[i][3] &&
                    pieces[i][0] != Piece.EMPTY) {
                    return new Winner( pieces[i][0], i, 0, i, 3);
            }
        }

        for (int j = 0; j < NUM_OF_ROWS; j++) {

            if (pieces[0][j] == pieces[1][j] &&
                    pieces[1][j] == pieces[2][j] &&
                    pieces[2][j] == pieces[3][j] &&
                    pieces[0][j] != Piece.EMPTY) {
                    return new Winner(pieces[0][j], j,0, j, 3);
            }
            if (pieces[1][j] == pieces[2][j] &&
                    pieces[2][j] == pieces[3][j] &&
                    pieces[3][j] == pieces[4][j] &&
                    pieces[1][j] != Piece.EMPTY) {
                    return new Winner(pieces[0][j], j,1, j, 4);
            }
            if (pieces[2][j] == pieces[3][j] &&
                    pieces[3][j] == pieces[4][j] &&
                    pieces[4][j] == pieces[5][j] &&
                    pieces[2][j] != Piece.EMPTY) {
                    return new Winner(pieces[0][j], j,2, j, 5);
            }
        }
        return new Winner(Piece.EMPTY);
    }
}
