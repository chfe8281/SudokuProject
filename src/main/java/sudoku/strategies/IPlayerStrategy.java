package sudoku.strategies;

import sudoku.boardgameparts.GroupsFactory;
import sudoku.boardgameparts.IBoard;
import sudoku.boardgameparts.SudokuBoard;
import sudoku.commands.ICommand;

public interface IPlayerStrategy {
    ICommand selectMove(IBoard playerBoard, IBoard targetBoard);
}
