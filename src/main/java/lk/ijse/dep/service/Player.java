package lk.ijse.dep.service;

public class Player {
    protected Board board;

    public Player(Board newBoard) {
        this.board = newBoard;
    }

    public void movePiece(int col) {
        boolean isLegal = board.isLegalMove(col);
        //System.out.println(isLegal);

        if (isLegal) {
           board.updateMove(col, Piece.BLUE);

           /// UI updated
           board.getBoardUI().update(col, true);

           Winner winner = board.findWinner();  /// check a winner

           if (winner.getWinningPiece() != Piece.EMPTY) {
               board.getBoardUI().notifyWinner(winner);   /// There is a winner
           }else {
               /// No winner yet, check if there are legal moves
               boolean isExistMoves = board.existLegalMoves();
               if (!isExistMoves) {
                   /// No more legal moves = tied game
                   board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
               }
           }
        }
    }
}
