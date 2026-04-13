package sudoku.boardgameparts;

public class Cell {
    private int value;
    private boolean isFixed;
    private boolean isWrong = false;
    private int rowPosition;
    private int columnPosition;


    public Cell(int row, int column){
        this.rowPosition = row;
        this.columnPosition = column;
    }

    public void setFixed(boolean fixed){
        this.isFixed = fixed;
    }

    public boolean isWrong(){return isWrong;}

    public void setWrong(boolean wrong){
        this.isWrong = wrong;
    }

    public boolean isFixed(){return isFixed;}
    public void setValue(int newValue){ this.value = newValue; }

    public int getValue(){ return this.value; }
}
