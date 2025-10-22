package lk.ijse.dep.service;

public class Winner {
    private int col1 = -1;
    private int col2 = -1;;
    private int row1 = -1;;
    private int row2 = -1;;
    Piece winningPiece;

    public Winner(Piece winningPiece) {
        this.winningPiece = winningPiece;
    }

    public Winner(int col1, int col2, int row1, int row2, Piece winningPiece) {
        this.col1 = col1;
        this.col2 = col2;
        this.row1 = row1;
        this.row2 = row2;
        this.winningPiece = winningPiece;
    }

    public int getCol1() {
        return this.col1;
    }

    public int getCol2() {
        return this.col2;
    }

    public int getRow1() {
        return this.row1;
    }

    public int getRow2() {
        return this.row2;
    }

    public Piece getWinningPiece() {
        return this.winningPiece;
    }

    public void setCol1() {
        this.col1 = col1;
    }

    public void setCol2() {
        this.col2 = col2;
    }

    public void setRow1() {
        this.row1 = row1;
    }

    public void setRow2() {
        this.row2 = row2;
    }

    public void setWinningPiece() {
        this.winningPiece = winningPiece;
    }

    @Override
    public String toString() {
        return "Winner{" +
                "col1=" + col1 +
                ", col2=" + col2 +
                ", row1=" + row1 +
                ", row2=" + row2 +
                ", winningPiece=" + winningPiece + '}';
    }
}
