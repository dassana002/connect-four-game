package lk.ijse.dep.service;

public class AiPlayer extends Player {
    public AiPlayer(Board newBoard) {
        super(newBoard);
    }

    @Override
    public void movePiece(int col) {
        int randomCol;

        do {
            randomCol = (int) (Math.random() * 6);     /// Generate random number
        }while (!board.isLegalMove(randomCol));         /// verify legal column

        /// update the board
        board.updateMove(randomCol, Piece.GREEN);

        /// notify UI about the move
        board.getBoardUI().update(randomCol, false);

        Winner winner = board.findWinner();   /// Check for winner

        if (winner.getWinningPiece() != Piece.EMPTY) {
            board.getBoardUI().notifyWinner(winner);   /// There is a winner
        } else if (board.existLegalMoves()){
            board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY)); /// no winner and no legal moves left = tied game
        }
    }
}
