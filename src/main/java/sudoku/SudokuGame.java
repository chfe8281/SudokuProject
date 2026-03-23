package sudoku;

public class SudokuGame {
    private SudokuBoard board;

    public SudokuGame(SudokuBoard board) {
        this.board = board;
    }

    public SudokuBoard getBoard() {
        return board;
    }
}
