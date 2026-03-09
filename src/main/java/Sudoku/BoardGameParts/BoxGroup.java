package Sudoku.BoardGameParts;

import java.util.List;
import java.lang.Math;

public class BoxGroup implements IGroup {
    private Integer boxSize; // Total number of cells in the box
    private Cell[][] cells;
    public BoxGroup(int size){
        this.boxSize = size;
        Integer boxDimension = (int) Math.sqrt(boxSize);
        cells = new Cell[boxDimension][boxDimension];
    }
    public List<Integer> unusedValues(){

    }

    public List<Integer> repeatedValues(){

    }


    public void setCells(List<Cell> cells){
        int iterator = 0;
        for(int i = 0; i < Math.sqrt(boxSize); i++) {
            for (int j = 0; j < Math.sqrt(boxSize); j++) {
                this.cells[i][j] = cells.get(iterator);
                iterator++;
            }
        }

    }
}
