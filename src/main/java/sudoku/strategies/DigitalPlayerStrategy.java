package sudoku.strategies;

import sudoku.SudokuBoard;
import sudoku.commands.CommandFactory;
import sudoku.commands.ICommand;

public class DigitalPlayerStrategy implements IPlayerStrategy{
    // fill first empty cell with valid number with backtracking
    CommandFactory commandFactory;
    public DigitalPlayerStrategy(CommandFactory commandFactory){
        this.commandFactory = commandFactory;
    }
    public ICommand selectMove(SudokuBoard playerBoard){
        for(int row = 0; row < playerBoard.getSize(); row++){
            for(int col = 0; col < playerBoard.getSize(); col++){
                if (playerBoard.getCellValue(row, col) == 0) { // empty
                    int number = findValidNumber(playerBoard, row, col);
                    if (number != 0) {
                        ICommand move = commandFactory.createMoveCommand(playerBoard, row, col, number);
                        move.execute();
                        if(canSolve(playerBoard)){
                            move.undo();
                            return move;
                        } else {
                            move.undo();
                        }
                        //playerBoard.setCellValue(row, col, number);
                        //System.out.println("Digital player placed " + number + " at (" + row + "," + col + ")");
                        // return;
                    }
                }
            }
        }
        return null;
    }

    private int findValidNumber(SudokuBoard playerBoard, int row, int col){
        for (int num = 1; num <= playerBoard.getSize(); num++) {
            System.out.println("Trying " + num + " at (" + row + "," + col + ")");
            if (playerBoard.isValidMove(row, col, num)) return num;
        }
        return 0;
    }

    private boolean canSolve(SudokuBoard board){
        for(int row = 0; row < board.getSize(); row++){
            for(int col = 0; col < board.getSize(); col++){
                if(!board.isFixed(row, col) && board.getCellValue(row, col) == 0){
                    for(int num = 1; num <= board.getSize(); num++){
                        if(board.isValidMove(row, col, num)){
                            board.setCellValue(row, col, num);
                            if(canSolve(board)) return true;
                            board.setCellValue(row, col, 0); // undo
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
