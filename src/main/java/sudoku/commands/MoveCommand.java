package sudoku.commands;

import sudoku.boardgameparts.GroupsFactory;
import sudoku.boardgameparts.IBoard;
import sudoku.boardgameparts.SudokuBoard;

public class MoveCommand implements ICommand{
    private IBoard board;
    private int row;
    private int col;
    private int input;
    private int previousValue;

    public MoveCommand(IBoard board, int row, int col, int input){
        this.board = board;
        this.row = row;
        this.col = col;
        this.input = input;
        this.previousValue = board.getCellValue(row, col);
    }

    @Override
    public void execute(){
        //game.tryMove(row, col, input);
    }

    @Override
    public void undo(){
        if(!board.isFixed(row, col)){
            board.setCellValue(row, col, previousValue);
        }
    }

}
