package Sudoku.BoardGameParts;

import java.util.ArrayList;
import java.util.List;

public class LineGroup implements IGroup {
    private int lineSize;
    private List<Cell> cells;

    public LineGroup(int size){this.lineSize = size;}
    public List<Integer> unusedValues(){
        return List.of();
    }

    public List<Integer> repeatedValues(){
        return List.of();
    }

    public void setCells(List<Cell> cells){
        this.cells = cells;
    }

    public List<Cell> getCells(){
        List<Cell> copy = new ArrayList<>(cells);
        return copy;
    }
}
