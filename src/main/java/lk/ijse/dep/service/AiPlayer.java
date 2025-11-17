package lk.ijse.dep.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        } else if (!board.existLegalMoves()) {
            board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY)); /// no winner and no legal moves left = tied game
        }
    }

    /**
     * Find best Move
     * 01. Can we win now?
     * 02. Can the human player win? --> Block!
     * 03. Find the best move (by simulating games)
     */
    private int findBestMove() {
        /// can AI win now?
        int winMove = getWinningMove(Piece.GREEN);
        if (winMove != -1) {
            System.out.println("AI found a winning move at column");
            return winMove;
        }
        /// Can the human player win? --> it is Block!
        int blockMove = getWinningMove(Piece.BLUE);
        if (blockMove != -1) {
            System.out.println("AI is Blocking human at column");
            return blockMove;
        }
        /// Find the best move (by simulating games)
        return getRandomLegalMoves();
    }

    /// generate random legal moves
    private int getRandomLegalMoves() {
        List<Integer> possibleMoves = new ArrayList<>();

        for (int col = 0; col < 6; col++) {
            if (board.isLegalMove(col)) {
                possibleMoves.add(col);
            }
        }
        int randomIndex = random.nextInt(possibleMoves.size());
        return possibleMoves.get(randomIndex);
    }

    /// Find the winning move spot in the column
    private int getWinningMove(Piece piece) {
        for (int col = 0; col < 6; col++) {
            if (board.isLegalMove(col)) {
                if (temporaryCheckWin(col, piece))
                    return col;   /// return winning move spot
            }
        }
        return -1;
    }

    /**
     * If you put this piece in this column, you will see if you will win.
     * the move is checked temporarily.
     */
    private boolean temporaryCheckWin(int col, Piece piece) {
        /// get Copy of board
        Piece[][] boardCopy = copyBoard();
        /// what an Available row in this column
        int row = board.findNextAvailableSpot(col);
        /// Put the piece in that place (in the copy).
        boardCopy[col][row] = piece;
        /// Check win the piece
        return isWinning(boardCopy, piece);
    }

    /// get Copy of Current Board
    private Piece[][] copyBoard() {
        Piece[][] original = board.getPieces();  /// get All pieces
        Piece[][] copy = new Piece[6][5];

        for (int col = 0; col < 6; col++) {
            for (int row = 0; row < 5; row++) {
                copy[col][row] = original[col][row];
            }
        }
        return copy;
    }

    /// who are check the win
    private boolean isWinning(Piece[][] boardCopy, Piece piece) {
        /// Vertical check
        for (int col = 0; col < 6; col++) {
            for (int row = 0; row <= 1; row++) {
                if (boardCopy[col][row] == piece &&
                        boardCopy[col][row + 1] == piece &&
                        boardCopy[col][row + 2] == piece &&
                        boardCopy[col][row + 3] == piece) return true;
            }
        }
        /// Horizontal check
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col <= 2; col++) {
                if (boardCopy[col][row] == piece &&
                        boardCopy[col + 1][row] == piece &&
                        boardCopy[col + 2][row] == piece &&
                        boardCopy[col + 3][row] == piece) return true;
            }
        }
        return false;
    }
}



