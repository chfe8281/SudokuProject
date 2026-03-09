package Sudoku.BoardGameParts;

import java.util.List;

public class LineGroup implements IGroup {
    private int lineSize;
    private List<Cell> cells;

    public LineGroup(int size){this.lineSize = size;}
    public List<Integer> unusedValues(){

    }

    public List<Integer> repeatedValues(){

    }

    public void setCells(List<Cell> cells){
        this.cells = cells;
    }
}
