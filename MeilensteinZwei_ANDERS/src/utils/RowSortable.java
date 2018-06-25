package utils;

import data.Grid;

public interface RowSortable {
	
	Grid getRowConflictFree(Grid grid);
	boolean hasRowConflictFree(Grid grid);
	Grid getRowSortColBlock(Grid grid);
	boolean hasRowSortColBlock(Grid grid);
	Grid getRowSudoku(Grid grid);
	boolean hasRowSudoku(Grid grid);
	int putNumberColBlock(Grid grid, int number);
}
