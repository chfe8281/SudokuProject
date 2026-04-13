package sudoku.strategies;

import sudoku.boardgameparts.GroupsFactory;
import sudoku.boardgameparts.IBoard;
import sudoku.boardgameparts.SudokuBoard;
import sudoku.commands.CommandFactory;
import sudoku.commands.ICommand;

public class DigitalPlayerStrategy implements IPlayerStrategy{
    // fill first empty cell with valid number with backtracking
    CommandFactory commandFactory;
    //ICommand lastMove;
    public DigitalPlayerStrategy(CommandFactory commandFactory){
        this.commandFactory = commandFactory;
    }
    public ICommand selectMove(IBoard playerBoard, IBoard targetBoard){
        SudokuBoard solverBoard;
        for(int row = 0; row < playerBoard.getSize(); row++){
            for(int col = 0; col < playerBoard.getSize(); col++){
                if (playerBoard.getCellValue(row, col) == 0) { // empty
                    int number = findValidNumber(playerBoard, targetBoard, row, col);
                    if (number != 0) {
                        ICommand move = commandFactory.createMoveCommand(playerBoard, row, col, number);
                        /*move.execute();
                        solverBoard = playerBoard;
                        if(canSolve(solverBoard)){
                            return move;
                        } else {
                            move.undo();
                        }*/
                        return move;
                        //playerBoard.setCellValue(row, col, number);
                        //System.out.println("Digital player placed " + number + " at (" + row + "," + col + ")");
                        // return;
                    }
                    else{
                        System.out.println("Couldn't find a move");
                        return null;

                    }
                }
            }
        }
        return null;
    }

    private int findValidNumber(IBoard playerBoard, IBoard targetBoard, int row, int col){
        for (int num = 1; num <= playerBoard.getSize(); num++) {
            System.out.println("Trying " + num + " at (" + row + "," + col + ")");
            if (playerBoard.isValidMove(row, col, num) && (num == targetBoard.getCellValue(row, col))){
                System.out.println(num);
                return num;
            }
            System.out.println("NOT VALID");
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
