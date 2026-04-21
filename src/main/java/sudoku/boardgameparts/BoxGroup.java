package sudoku.boardgameparts;

import java.util.ArrayList;
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


    public void setCells(List<Cell> cells){
        int size = (int) Math.sqrt(boxSize);

        for (int index = 0; index < cells.size(); index++) {
            int row = index / size;
            int col = index % size;

            this.cells[row][col] = cells.get(index);
        }
    }


    public List<Cell> getCells(){
        List<Cell> cellList = new ArrayList<>();
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                cellList.add(cells[i][j]);
            }
        }
        return cellList;
    }
}
