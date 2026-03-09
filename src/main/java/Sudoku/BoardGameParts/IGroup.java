package Sudoku.BoardGameParts;

import java.util.List;

public interface IGroup {
    public abstract void setCells(List<Cell> cells);
    public abstract List<Integer> unusedValues();

    public abstract List<Integer> repeatedValues();



}
