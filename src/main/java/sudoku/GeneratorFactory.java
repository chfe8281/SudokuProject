package sudoku;

public class GeneratorFactory {
    private int STANDARD_BOARD_SIZE = 9;

    public SudokuGenerator createGenerator(int boardSize){
        return new SudokuGenerator(boardSize);
    }

    public SudokuGenerator createStandardGenerator(){
        return new SudokuGenerator(STANDARD_BOARD_SIZE);
    }
}
