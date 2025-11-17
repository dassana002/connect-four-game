package lk.ijse.dep.service;

public class HumanPlayer extends Player {
    public HumanPlayer(Board newBoard) {
        super(newBoard);
    }

    @Override
    public void movePiece(int col) {

        // return if illegal move
        if (!board.isLegalMove(col)) return;

        /// update the board
        board.updateMove(col, Piece.BLUE);

        /// Notify UI about the move
        board.getBoardUI().update(col, true);

        /// check for winner
        Winner winner = board.findWinner();

        if (winner.getWinningPiece() != Piece.EMPTY) {
            board.getBoardUI().notifyWinner(winner);   /// There is a winner
        } else if (!board.existLegalMoves()){
            board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY)); /// no winner and no legal moves left = tied game
        }
    }
}
