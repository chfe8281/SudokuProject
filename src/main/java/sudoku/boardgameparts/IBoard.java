package sudoku.boardgameparts;

public interface IBoard {

    public void printBoard();

    public int getBoxSize();

    public boolean isFilled();

    public boolean isFixed(int row, int col);

    public boolean isValidMove(int row, int col, int input);

    public void setCellValue(int row, int col, int input);

    public int getCellValue(int row, int col);
    public void setIsWrong(int row, int col, boolean isWrong);

    public int getSize();

    public boolean isWrong(int row, int col);

    public boolean equals(Object obj);
}
