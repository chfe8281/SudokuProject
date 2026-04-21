package sudoku.boardgameparts;

import java.util.ArrayList;
import java.util.List;

public class LineGroup implements IGroup {
    private int lineSize;
    private List<Cell> cells;

    public LineGroup(int size){this.lineSize = size;}

    public void setCells(List<Cell> cells){
        this.cells = cells;
    }

    public List<Cell> getCells(){
        List<Cell> copy = new ArrayList<>(cells);
        return copy;
    }
}
