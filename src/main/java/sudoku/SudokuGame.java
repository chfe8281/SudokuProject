package sudoku;

public class SudokuGame {
    private SudokuBoard targetBoard;
    private SudokuBoard playerBoard;

    public SudokuGame(SudokuBoard board) {
        this.targetBoard = board;
    }

    public SudokuBoard getBoard() {
        return targetBoard;
    }
}
