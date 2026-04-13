package sudoku.commands;

import sudoku.boardgameparts.GroupsFactory;
import sudoku.boardgameparts.SudokuBoard;

public class MoveCommand implements ICommand{
    private SudokuBoard board;
    private int row;
    private int col;
    private int input;
    private int previousValue;

    public MoveCommand(SudokuBoard board, int row, int col, int input){
        this.board = board;
        this.row = row;
        this.col = col;
        this.input = input;
        this.previousValue = board.getCellValue(row, col);
    }

    @Override
    public void execute(){
        if (board.isFixed(row, col)){
            System.out.println("Cannot change a fixed cell at (" + row + "," + col + ")");
            return;
        }
        board.setCellValue(row, col, input);
    }

    @Override
    public void undo(){
        if(!board.isFixed(row, col)){
            board.setCellValue(row, col, previousValue);
        }
    }

}
