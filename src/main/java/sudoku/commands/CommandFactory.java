package sudoku.commands;

import sudoku.SudokuBoard;

public class CommandFactory {

    public ICommand createMoveCommand(SudokuBoard board, int row, int col, int input){
        return new MoveCommand(board, row, col, input);
    }
}
