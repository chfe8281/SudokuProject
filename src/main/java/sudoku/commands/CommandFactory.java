package sudoku.commands;

import sudoku.boardgameparts.GroupsFactory;
import sudoku.boardgameparts.IBoard;
import sudoku.boardgameparts.SudokuBoard;

public class CommandFactory {

    public ICommand createMoveCommand(IBoard board, IBoard targetBoard, int row, int col, int input){
        return new MoveCommand(board, targetBoard, row, col, input);
    }
}
