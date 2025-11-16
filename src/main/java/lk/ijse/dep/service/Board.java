package lk.ijse.dep.service;

public interface Board {
    final int NUM_OF_ROWS = 5;
    final int NUM_OF_COLUMNS = 6;

    BoardUI getBoardUI();

    int findNextAvailableSpot(int col);

    boolean isLegalMove(int col);

    boolean existLegalMoves();

    void updateMove(int col, Piece move);

    Winner findWinner();
}
