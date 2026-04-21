package sudoku.boardgameparts;

public class CellFactory {

    public Cell[][] createCells(int width, int height){
        Cell[][] newCells = new Cell[width][height];
        for(int i = 1; i < width+1; i++){
            for(int j = 1; j < height+1; j++){
                newCells[i-1][j-1] = new Cell(i, j);
            }
        }

        return newCells;
    }

}
