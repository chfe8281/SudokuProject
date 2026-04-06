package sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuGenerator {
    private int boardSize;
    private int boxSize;
    public SudokuGenerator(int boardSize){
        this.boardSize = boardSize;
        this.boxSize = (int) Math.sqrt(boardSize);

        if(boxSize * boxSize != boardSize){
            throw new IllegalArgumentException("Size must be a perfect square.");
        }
    }
    public int[][] generateSolution(){
        int[][] board = new int[boardSize][boardSize];
        fill(board, 0, 0);
        return board;
    }

    private boolean fill(int[][]board, int row, int col){
        if(row == boardSize){
            return true;
        }

        int nextRow = (col == boardSize -1) ? row + 1: row;
        int nextCol = (col + 1) % boardSize;

        List<Integer> nums = getShuffledNumbers();

        for(int num : nums){
            if(isValid(board, row, col, num)){
                board[row][col] = num;
                if(fill(board, nextRow, nextCol)){
                    return true;
                }
            }
            board[row][col] = 0;

        }

        return false;
    }

    private List<Integer> getShuffledNumbers(){
        List<Integer> nums = new ArrayList<>();
        for(int i = 1; i <= boardSize; i++){
            nums.add(i);
        }
        Collections.shuffle(nums);
        return nums;
    }

    private boolean isValid(int[][] board, int row, int col, int input){
        for(int i = 0; i < boardSize; i++){
            if(board[row][i] == input || board[i][col] == input){
                return false;
            }
        }

        int startRow = (row / boxSize) * boxSize;
        int startCol = (col / boxSize) * boxSize;

        for(int r = 0; r < boxSize; r++){
            for(int c = 0; c < boxSize; c++){
                if(board[startRow + r][startCol + c] == input){
                    return false;
                }
            }
        }
        return true;
    }
}
