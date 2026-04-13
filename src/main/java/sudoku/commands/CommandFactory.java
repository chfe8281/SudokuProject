package sudoku.commands;

import sudoku.boardgameparts.GroupsFactory;
import sudoku.boardgameparts.SudokuBoard;

public class CommandFactory {

    public ICommand createMoveCommand(SudokuBoard board, int row, int col, int input){
        return new MoveCommand(board, row, col, input);
    }
}
