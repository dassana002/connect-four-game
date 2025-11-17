package lk.ijse.dep.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static lk.ijse.dep.service.Board.NUM_OF_COLUMNS;

public class AiPlayer extends Player {
    private final Random random;

    public AiPlayer(Board newBoard) {
        super(newBoard);
        this.random = new Random();
    }

    @Override
    public void movePiece(int col) {
        /// find best move
        int randomCol = findBestMove();
        /// update the board
        board.updateMove(randomCol, Piece.GREEN);
        /// notify UI about the move
        board.getBoardUI().update(randomCol, false);
        /// Who are the winner
        Winner winner = board.findWinner();

        if (winner.getWinningPiece() != Piece.EMPTY) {
            board.getBoardUI().notifyWinner(winner);   /// There is a winner
        } else if (!board.existLegalMoves()){
            board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY)); /// no winner and no legal moves left = tied game
        }
    }

    /**
     *          Find best Move
     * 01. Can we win now?
     * 02. Can the human player win? --> Block!
     * 03. Find the best move (by simulating games)
     */
    private int findBestMove() {
        /// Find the best move (by simulating games)
        return getRandomLegalMoves();
    }

    private int getRandomLegalMoves() {
        List<Integer> possibleMoves = new ArrayList<>();

        for (int col = 0; col < 6; col++) {
            if (board.isLegalMove(col)) {
                possibleMoves.add(col);
            }
        }
        System.out.println(possibleMoves);
        System.out.println(random.nextInt(possibleMoves.size()));

        int randomIndex = random.nextInt(possibleMoves.size());
        return possibleMoves.get(randomIndex);
    }
}
