package sudoku.commands;

import sudoku.boardgameparts.GroupsFactory;
import sudoku.boardgameparts.IBoard;
import sudoku.boardgameparts.SudokuBoard;

public class MoveCommand implements ICommand{
    private IBoard board;
    private IBoard targetBoard;
    private int row;
    private int col;
    private int input;
    private int previousValue;

    public MoveCommand(IBoard board, IBoard targetBoard, int row, int col, int input){
        this.board = board;
        this.targetBoard = targetBoard;
        this.row = row;
        this.col = col;
        this.input = input;
        this.previousValue = board.getCellValue(row, col);
    }

    @Override
    public void execute(){
        //game.tryMove(row, col, input);
        if (!board.isFixed(row, col) ) { //&& board.isValidMove(row, col, input)
            board.setCellValue(row, col, input);

            // mark wrong

            if (targetBoard.getCellValue(row, col) != input) {
                board.setIsWrong(row, col, true);
            } else {
                board.setIsWrong(row, col, false);
            }

        }
    }

}
