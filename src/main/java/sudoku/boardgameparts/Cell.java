package sudoku.boardgameparts;

public class Cell {
    private int value;
    private int rowPosition;
    private int columnPosition;


    public Cell(int row, int column){
        this.rowPosition = row;
        this.columnPosition = column;
    }

    public void setValue(int newValue){ this.value = newValue; }

    public int getValue(){ return this.value; }
}
