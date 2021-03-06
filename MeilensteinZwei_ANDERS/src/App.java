import java.util.LinkedList;
import java.util.List;

import data.Cell;
import data.Grid;
import utils.GridUtils3;
import utils.PuzzleRow;
import utils.RowUtils;

public class App {
	
	public static void main(String[] args) {
		Grid a = new Grid(9);
		Grid b = new Grid(9);
		RowUtils abc = new RowUtils();
		GridUtils3 cba = new GridUtils3();
		PuzzleRow cab = new PuzzleRow();
		
		//DEFAULT
		int[] row1 = {-1,+2,-1,-1,-1,-1,-1,-1,-1};
		int[] row2=  {-1,-1,-1,+2,-1,-1,-1,-1,-1};
		int[] row3 = {-1,-1,-1,-1,-1,-1,+2,-1,-1};
		int[] row4 = {-1,-1,+2,-1,-1,-1,-1,-1,-1};
		int[] row5 = {-1,-1,-1,-1,+2,-1,-1,-1,-1};
		int[] row6 = {-1,-1,-1,-1,-1,-1,-1,+2,-1};
		int[] row7 = {-1,-1,-1,-1,-1,-1,-1,-1,+2};
		int[] row8 = {-1,-1,-1,-1,-1,+2,-1,-1,-1};
		int[] row9 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
				
		//TWO TURN
		int[] row11 = {+9,-1,-1,-1,-1,-1,-1,-1,-1};
		int[] row22=  {-1,+8,-1,-1,-1,-1,-1,-1,-1};
		int[] row33 = {-1,-1,+7,-1,-1,-1,-1,-1,-1};
		int[] row44 = {-1,-1,-1,+6,-1,-1,-1,-1,-1};
		int[] row55 = {-1,-1,-1,-1,+5,-1,-1,-1,-1};
		int[] row66 = {-1,-1,-1,-1,-1,+4,-1,-1,-1};
		int[] row77 = {-1,-1,-1,-1,-1,-1,+3,-1,-1};
		int[] row88 = {-1,-1,-1,-1,-1,-1,-1,+2,-1};
		int[] row99 = {-1,-1,-1,-1,-1,-1,-1,-1,+1};
		
		fillRow(a, 1, row1);
		fillRow(a, 2, row2);
		fillRow(a, 3, row3);
		fillRow(a, 4, row4);
		fillRow(a, 5, row5);
		fillRow(a, 6, row6);
		fillRow(a, 7, row7);
		fillRow(a, 8, row8);
		fillRow(a, 9, row9);
		
		fillRow(b, 1, row11);
		fillRow(b, 2, row22);
		fillRow(b, 3, row33);
		fillRow(b, 4, row44);
		fillRow(b, 5, row55);
		fillRow(b, 6, row66);
		fillRow(b, 7, row77);
		fillRow(b, 8, row88);
		fillRow(b, 9, row99);
		
		callPuzzleRow(a, cab);
//		callGridUtils3(a, b, cba);
//		callRowUtils(a, b, abc);
//		callSolveRowBased(a, b, abc);
		
	}
	
	public static void callPuzzleRow(Grid a, PuzzleRow cab){
		System.out.println("GetRowConflictFree:");
		(cab.getRowConflictFree(a)).print();
		
		System.out.println("\nHasRowConflictFree: " + cab.hasRowConflictFree(a));
		
		System.out.println("\ngetRowSortColBlock: ");
		(cab.getRowSortColBlock(a)).print();
		
		System.out.println("\nHasRowSortColBlock: " + cab.hasRowSortColBlock(a));
		
		System.out.println("\ngetRowSudoku: ");
		(cab.getRowSudoku(a)).print();
		
		System.out.println("\nHasRowSudoku: " + cab.hasRowSudoku(a));
		
		System.out.println("\nputNumberColBlock: " + cab.putNumberColBlock(a, 2));
	}

	public static void callSolveRowBased(Grid a, Grid b, RowUtils abc){
		
//		System.out.println(abc.solveRowBased(a).get(0));
//		a.print();
//		System.out.println(abc.solveRowBased(a).size());
		
//		listAuslesen(allGrids);
		abc.solveRowBased(a);
		listAuslesen(abc.solveRowBased(a));
//		System.out.println(abc.solveRowBased(a).toString());
	}
	
	public void callGridUtils3(Grid a, Grid b, GridUtils3 cba){
		a.print();
		System.out.println("");
//		b.print();
//		System.out.println("");
		
		System.out.println("turnRight");
		cba.turnRight(a);
		a.print();
		System.out.println("");
		
//		System.out.println("getGridTurnNumber: "+cba.getGridTurnNumber(a, b));
//		System.out.println("isGridTurn: " + cba.isGridTurn(a, b));
	}
		
	public void callRowUtils(Grid a, Grid b, RowUtils abc){
		int[] rImage = {2,3,1};
		int[] vImage = {9,8,7,6,5,4,3,2,1};
		
		a.print();
		System.out.println("");
//		b.print();
//		System.out.println("");
			
//		System.out.println("isValidRow: "+abc.isValidRow(a, a.getCell(1, 1)));
//		System.out.println("hasFullHouseRow: "+abc.hasFullHouseRow(a));
//		System.out.println("isFullHouseRow: "+abc.isFullHouseRow(a, a.getCell(1, 1)));
//		System.out.println("isRowWithNakedSingleCell: "+abc.isRowWithNakedSingleCell(a, a.getCell(9, 1)));
//		System.out.println("getRowMinimalNakedSingleCell: " + abc.getRowMinimalNakedSingleCell(a, a.getCell(9, 1)).getrIndex() + "," + abc.getRowMinimalNakedSingleCell(a, a.getCell(9, 1)).getcIndex());
//		System.out.println("isRowWithHiddenSingleCell: "+ abc.isRowWithHiddenSingleCell(a, a.getCell(9, 1)));
//		System.out.println("getRowMinimalHiddenSingleCell: "+ abc.getRowMinimalHiddenSingleCell(a, a.getCell(9, 1)).getrIndex() + "," + abc.getRowMinimalHiddenSingleCell(a, a.getCell(9, 1)).getcIndex());
//		System.out.println("getRowMinimalHiddenSingleCell: "+ abc.getRowMinimalHiddenSingleCell(a, a.getCell(1, 1)).getrIndex() + " " + abc.getRowMinimalHiddenSingleCell(a, a.getCell(1, 1)).getcIndex());
//		System.out.println("isRowWithNakedPairCells: "+ abc.isRowWithNakedPairCells(a, a.getCell(9, 1)));
//		System.out.println("getRowMinimalNakedPairCells: ");
//		auslesen(abc.getRowMinimalNakedPairCells(a, a.getCell(9, 1)));
//		
//		System.out.println("applyBlockInternRowPermutation");abc.applyBlockInternRowPermutation(a, a.getCell(1, 1), rImage);
//		a.print();
//		
//		System.out.println("isBlockInternRowPermutation: " + abc.isBlockInternRowPermutation(a, b));
//		
//		System.out.println("getBlockInternRowPermutationImage: ");
//		if(abc.getBlockInternRowPermutationImage(a, b, a.getCell(1, 1)) == null){
//			System.out.println("null");
//		}else{
//			for(int i = 0; i < abc.getBlockInternRowPermutationImage(a, b, a.getCell(1, 1)).length; i++){
//			System.out.print(abc.getBlockInternRowPermutationImage(a, b, a.getCell(1, 1))[i]);
//			}
//		}
//		
//		System.out.println("applyRowValuePermutation");
//		abc.applyRowValuePermutation(a, a.getCell(1, 2), vImage);
//		a.print();
//		
//		System.out.println("getRowValuePermutationImage: ");
//		auslesen(abc.getRowValuePermutationImage(a, b, a.getCell(1, 2)));
//		
//		System.out.println("isRowValuePermutation: "+abc.isRowValuePermutation(a, b, a.getCell(1, 1)));
//		System.out.println("getRowWhiteSpaces: " + abc.getRowWhiteSpaces(a, a.getCell(1, 1)));
//		
		System.out.println("getRowMinimalHiddenPairCells: ");
		auslesen(abc.getRowMinimalHiddenPairCells(a, a.getCell(1, 1)));
//		
//		System.out.println("isRowMinimalHiddenPairCell: " + abc.isRowWithHiddenPairCells(a, a.getCell(9, 1)));
	}
		
	public static void fillRow(Grid grid, int row, int[] values){
		
		for(int i = 1; i <= values.length; i++){
			grid.setValue(row,i,values[i-1]);
		}
	}
	
	public void auslesen(int[] a){
		if(a == null){
			System.out.println("null");
		}else{
			for(int i = 0; i < a.length; i++){
				System.out.print(a[i]+",");
			}
		}
		System.out.println("");
	}
	
	public void auslesen(Cell[] a){
		if(a[0] == null || a[1] == null){
			System.out.println("null");
		}else{
			for(int i = 0; i < a.length; i++){
				System.out.print(a[i].getrIndex()+","+a[i].getcIndex());
				System.out.println("");
			}
		}
		
	}
	
	public static void listAuslesen(List<Grid> list){
		for(int i = 0; i < list.size(); i++){
			list.get(i).print();
			System.out.println("");
		}
		System.out.println("");
	}
	
	
//	//DEFAULT
//	int[] row1 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row2=  {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row3 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row4 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row5 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row6 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row7 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row8 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row9 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	
//	//putNumberColBlock INPUT
//	int[] row1 = {+5,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row2=  {-1,+2,-1,-1,-1,-1,-1,+5,-1};
//	int[] row3 = {-1,-1,+7,+7,+7,-1,-1,-1,-1};
//	int[] row4 = {-1,-1,-1,-1,+5,-1,-1,-1,-1};
//	int[] row5 = {-1,-1,-1,-1,+3,-1,-1,-1,+5};
//	int[] row6 = {-1,+5,-1,-1,-1,-1,-1,-1,-1};
//	int[] row7 = {-1,-1,+5,-1,-1,-1,-1,-1,-1};
//	int[] row8 = {-1,-1,+4,-1,-1,-1,-1,-1,-1};
//	int[] row9 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	
//	//PUTNUMBERCOLBLOCK 2ND INPUT
//	int[] row1 = {-1,-1,-1,-1,-1,-1,-1,-1,+6};
//	int[] row2=  {-1,-1,-1,+6,-1,-1,-1,+2,-1};
//	int[] row3 = {-1,+4,+6,-1,-1,-1,+7,-1,-1};
//	int[] row4 = {-1,-1,-1,-1,-1,-1,+5,-1,-1};
//	int[] row5 = {-1,-1,-1,-1,+3,+6,+2,-1,-1};
//	int[] row6 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row7 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row8 = {-1,-1,-1,-1,-1,-1,-1,+6,-1};
//	int[] row9 = {-1,-1,-1,-1,+6,-1,-1,-1,-1};
//			
//	//putNumberColBlock TESTWEISE INPUT
//	int[] row1 = {-1,-1,+2,-1,-1,-1,-1,-1,-1};
//	int[] row2=  {+1,-1,-1,-1,-1,-1,-1,+2,-1};
//	int[] row3 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row4 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row5 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row6 = {-1,-1,+1,-1,-1,-1,-1,-1,-1};
//	int[] row7 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row8 = {-1,-1,-1,-1,+7,+7,-1,+1,-1};
//	int[] row9 = {-1,+3,-1,+1,-1,-1,-1,-1,-1};
//			
//	//getRowSudoku INPUT		
//	int[] row1 = {+8,+1,+6,+7,+3,+4,+2,+5,+9};
//	int[] row2 = {+1,+6,+8,+9,+4,+2,+5,+3,+7};
//	int[] row3 = {+6,+8,+2,+1,+9,+3,+7,+4,+5};
//	int[] row4 = {+3,+7,+4,+2,+6,+5,+8,+9,+1};
//	int[] row5 = {+2,+3,+5,+6,+1,+9,+4,+7,+8};
//	int[] row6 = {+7,+2,+3,+8,+5,+1,+9,+6,+4};
//	int[] row7 = {+4,+5,+9,+3,+7,+6,+1,+8,+2};
//	int[] row8 = {+9,+4,+7,+5,+2,+8,+6,+1,+3};
//	int[] row9 = {+5,+9,+1,+4,+8,+7,+3,+2,+6};
//	
//	//getRowSortColBlock INPUT		
//	int[] row1 = {+2,+3,+1,+2,+3,+1,+2,+3,+1};
//	int[] row2 = {+1,+2,+3,+1,+2,+3,+1,+2,+3};
//	int[] row3 = {+8,+9,+7,+8,+9,+7,+8,+9,+7};
//	int[] row4 = {+3,+1,+2,+3,+1,+2,+3,+1,+2};
//	int[] row5 = {+5,+6,+4,+5,+6,+4,+5,+6,+4};
//	int[] row6 = {+9,+7,+8,+9,+7,+8,+9,+7,+8};
//	int[] row7 = {+6,+4,+5,+6,+4,+5,+6,+4,+5};
//	int[] row8 = {+4,+5,+6,+4,+5,+6,+4,+5,+6};
//	int[] row9 = {+7,+8,+9,+7,+8,+9,+7,+8,+9};
//	
//	//getRowColSortBlock OUTPUT
//	int[] row1 = {+1,+2,+3,+1,+2,+3,+1,+2,+3};
//	int[] row2=  {+4,+5,+6,+4,+5,+6,+4,+5,+6};
//	int[] row3 = {+7,+8,+9,+7,+8,+9,+7,+8,+9};
//	int[] row4 = {+3,+1,+2,+3,+1,+2,+3,+1,+2};
//	int[] row5=  {+6,+4,+5,+6,+4,+5,+6,+4,+5};
//	int[] row6 = {+9,+7,+8,+9,+7,+8,+9,+7,+8};
//	int[] row7 = {+2,+3,+1,+2,+3,+1,+2,+3,+1};
//	int[] row8=  {+5,+6,+4,+5,+6,+4,+5,+6,+4};
//	int[] row9 = {+8,+9,+7,+8,+9,+7,+8,+9,+7};
//	
//	//v-konfliktes Sudoku
//	int[] row1 = {+2,+2,+2,+2,+2,+2,+2,+2,+2};
//	int[] row2=  {+2,+2,+2,+2,+2,+2,+2,+2,+2};
//	int[] row3 = {+2,+2,+2,+2,+2,+2,+2,+2,+2};
//	int[] row4 = {+1,+1,+1,+1,+1,+1,+1,+1,+1};
//	int[] row5 = {+2,+2,+2,+2,+2,+2,+2,+2,+2};
//	int[] row6 = {+1,+1,+1,+1,+1,+1,+1,+1,+1};
//	int[] row7 = {+1,+1,+1,+1,+1,+1,+1,+1,+1};
//	int[] row8 = {+1,+1,+1,+1,+1,+1,+1,+1,+1};
//	int[] row9 = {+2,+2,+2,+2,+2,+2,+2,+2,+2};
//	
//	//LoeSBARES LEICHTES SUDOKU
//	int[] row1 = {-1,+5,+9,+3,+7,-1,+1,-1,-1};
//	int[] row2=  {-1,-1,-1,-1,+4,-1,+5,-1,+7};
//	int[] row3 = {+7,+2,-1,+8,+5,-1,-1,-1,+4};
//	int[] row4 = {-1,-1,-1,+2,-1,-1,+8,-1,+1};
//	int[] row5 = {+5,+9,+1,-1,-1,-1,+3,+2,+6};
//	int[] row6 = {+6,-1,+2,+1,-1,-1,-1,-1,-1};
//	int[] row7 = {+8,-1,-1,-1,+3,+4,-1,+5,+9};
//	int[] row8 = {+9,-1,+7,-1,+2,-1,-1,-1,-1};
//	int[] row9 = {-1,-1,+5,-1,+1,+9,+4,+7,-1};
//	
//	//LoeSBARES LEICHTES SUDOKU LoeSUNG
//	[4, 5, 9, 3, 7, 6, 1, 8, 2]
//	[1, 6, 8, 9, 4, 2, 5, 3, 7]
//	[7, 2, 3, 8, 5, 1, 9, 6, 4]
//	[3, 7, 4, 2, 6, 5, 8, 9, 1]
//	[5, 9, 1, 4, 8, 7, 3, 2, 6]
//	[6, 8, 2, 1, 9, 3, 7, 4, 5]
//	[8, 1, 6, 7, 3, 4, 2, 5, 9]
//	[9, 4, 7, 5, 2, 8, 6, 1, 3]
//	[2, 3, 5, 6, 1, 9, 4, 7, 8]
//		
//	//LoeSBARES SUDOKU SEHR SCHWER
//	int[] row1 = {-1,-1,-1,+4,-1,+1,-1,-1,-1};
//	int[] row2=  {+1,-1,+6,-1,-1,-1,+4,-1,+7};
//	int[] row3 = {-1,-1,-1,-1,+9,-1,-1,-1,-1};
//	int[] row4 = {+6,-1,+8,-1,-1,-1,+2,-1,+9};
//	int[] row5 = {-1,-1,-1,-1,+6,-1,-1,-1,-1};
//	int[] row6 = {+3,-1,+9,-1,-1,-1,+5,-1,+6};
//	int[] row7 = {-1,-1,-1,-1,+1,-1,-1,-1,-1};
//	int[] row8 = {+4,-1,+2,-1,-1,-1,+7,-1,+1};
//	int[] row9 = {-1,-1,-1,+6,-1,+4,-1,-1,-1};
//	
//	//ONE HIDDEN PAIR
//	int[] row1 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row2=  {-1,-1,-1,+1,-1,-1,-1,-1,-1};
//	int[] row3 = {-1,-1,-1,-1,-1,-1,+1,-1,+9};
//	int[] row4 = {-1,+1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row5 = {-1,-1,-1,-1,+1,-1,-1,-1,-1};
//	int[] row6 = {-1,-1,-1,-1,-1,-1,-1,+1,+8};
//	int[] row7 = {-1,+9,+1,-1,+8,-1,-1,-1,-1};
//	int[] row8 = {-1,+8,-1,-1,+9,+1,-1,-1,-1};
//	int[] row9 = {+7,+6,+5,-1,-1,-1,-1,-1,-1};	
//	
//	//HIDDEN SINGLE
//	int[] row1 = {+1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row2=  {-1,-1,-1,+1,-1,-1,-1,-1,-1};
//	int[] row3 = {-1,-1,-1,-1,-1,-1,+1,-1,-1};
//	int[] row4 = {-1,+1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row5 = {-1,-1,-1,-1,+1,-1,-1,-1,-1};
//	int[] row6 = {-1,-1,-1,-1,-1,-1,-1,+1,-1};
//	int[] row7 = {-1,-1,+1,-1,-1,-1,-1,-1,-1};
//	int[] row8 = {-1,-1,-1,-1,-1,+1,-1,-1,-1};
//	int[] row9 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	
//	//TWO HIDDEN PAIR CELLS
//	int[] row1 = {+1,-1,-1,-1,-1,-1,-1,-1,+2};
//	int[] row2=  {+8,-1,-1,+1,+9,+2,-1,-1,-1};
//	int[] row3 = {+9,-1,+2,+8,-1,-1,+1,-1,-1};
//	int[] row4 = {-1,+1,+8,-1,-1,-1,+2,-1,+9};
//	int[] row5 = {-1,-1,+9,+2,+1,+8,-1,-1,-1};
//	int[] row6 = {-1,+2,-1,-1,-1,+9,-1,+1,+8};
//	int[] row7 = {-1,+8,+1,-1,+2,-1,+9,-1,-1};
//	int[] row8 = {+2,+9,-1,-1,-1,+1,-1,+8,-1};
//	int[] row9 = {-1,-1,-1,+9,+8,-1,-1,-1,-1};
//			
//	//NAKED PAIR
//	int[] row1 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row2=  {-1,-1,-1,-1,-1,+1,-1,-1,-1};
//	int[] row3 = {-1,-1,+1,-1,-1,-1,-1,-1,-1};
//	int[] row4 = {-1,-1,-1,-1,-1,-1,-1,+1,-1};
//	int[] row5 = {-1,-1,-1,-1,+1,-1,-1,-1,-1};
//	int[] row6 = {-1,+1,-1,-1,-1,+8,-1,-1,-1};
//	int[] row7 = {-1,-1,-1,-1,-1,-1,+1,-1,-1};
//	int[] row8 = {-1,-1,-1,+1,+7,-1,+2,-1,-1};
//	int[] row9 = {+1,+2,+3,+4,+5,+6,+9,-1,-1};
//	
//	//NAKED SINGLE
//	int[] row1 = {-1,-1,-1,-1,-1,-1,-1,-1,+4};
//	int[] row2=  {-1,-1,-1,-1,-1,-1,-1,-1,+5};
//	int[] row3 = {-1,-1,-1,-1,-1,-1,-1,-1,+6};
//	int[] row4 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row5 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row6 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row7 = {-1,-1,-1,-1,-1,-1,-1,+8,+9};
//	int[] row8 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row9 = {+1,+2,+3,-1,-1,-1,-1,-1,-1};
//	
//	//VALIDES SUDOKU
//	int[] row1 = {+4,+3,+5,+2,+6,+9,+7,+8,+1};
//	int[] row2=  {+6,+8,+2,+5,+7,+1,+4,+9,+3};
//	int[] row3 = {+1,+9,+7,+8,+3,+4,+5,+6,+2};
//	int[] row4 = {+8,+2,+6,+1,+9,+5,+3,+4,+7};
//	int[] row5 = {+3,+7,+4,+6,+8,+2,+9,+1,+5};
//	int[] row6 = {+9,+5,+1,+7,+4,+3,+6,+2,+8};
//	int[] row7 = {+5,+1,+9,+3,+2,+6,+8,+7,+4};
//	int[] row8 = {+2,+4,+8,+9,+5,+7,+1,+3,+6};
//	int[] row9 = {+7,+6,+3,+4,+1,+8,+2,+5,+9};
//	
//	//TWO TURN
//	int[] row11 = {+9,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row22=  {-1,+8,-1,-1,-1,-1,-1,-1,-1};
//	int[] row33 = {-1,-1,+7,-1,-1,-1,-1,-1,-1};
//	int[] row44 = {-1,-1,-1,+6,-1,-1,-1,-1,-1};
//	int[] row55 = {-1,-1,-1,-1,+5,-1,-1,-1,-1};
//	int[] row66 = {-1,-1,-1,-1,-1,+4,-1,-1,-1};
//	int[] row77 = {-1,-1,-1,-1,-1,-1,+3,-1,-1};
//	int[] row88 = {-1,-1,-1,-1,-1,-1,-1,+2,-1};
//	int[] row99 = {-1,-1,-1,-1,-1,-1,-1,-1,+1};
//	
//	//0 bzw 4 TURN
//	int[] row11 = {+1,-1,-1,-1,-1,-1,-1,-1,-1};
//	int[] row22=  {-1,+2,-1,-1,-1,-1,-1,-1,-1};
//	int[] row33 = {-1,-1,+3,-1,-1,-1,-1,-1,-1};
//	int[] row44 = {-1,-1,-1,+4,-1,-1,-1,-1,-1};
//	int[] row55 = {-1,-1,-1,-1,+5,-1,-1,-1,-1};
//	int[] row66 = {-1,-1,-1,-1,-1,+6,-1,-1,-1};
//	int[] row77 = {-1,-1,-1,-1,-1,-1,+7,-1,-1};
//	int[] row88 = {-1,-1,-1,-1,-1,-1,-1,+8,-1};
//	int[] row99 = {-1,-1,-1,-1,-1,-1,-1,-1,+9};
//	
//	//ONE TURN
//	int[] row11 = {-1,-1,-1,-1,-1,-1,-1,-1,+1};
//	int[] row22=  {-1,-1,-1,-1,-1,-1,-1,+2,-1};
//	int[] row33 = {-1,-1,-1,-1,-1,-1,+3,-1,-1};
//	int[] row44 = {-1,-1,-1,-1,-1,+4,-1,-1,-1};
//	int[] row55 = {-1,-1,-1,-1,+5,-1,-1,-1,-1};
//	int[] row66 = {-1,-1,-1,+6,-1,-1,-1,-1,-1};
//	int[] row77 = {-1,-1,+7,-1,-1,-1,-1,-1,-1};
//	int[] row88 = {-1,+8,-1,-1,-1,-1,-1,-1,-1};
//	int[] row99 = {+9,-1,-1,-1,-1,-1,-1,-1,-1};
//	
//	//THREE TURN
//	int[] row11 = {-1,-1,-1,-1,-1,-1,-1,-1,+9};
//	int[] row22=  {-1,-1,-1,-1,-1,-1,-1,+8,-1};
//	int[] row33 = {-1,-1,-1,-1,-1,-1,+7,-1,-1};
//	int[] row44 = {-1,-1,-1,-1,-1,+6,-1,-1,-1};
//	int[] row55 = {-1,-1,-1,-1,+5,-1,-1,-1,-1};
//	int[] row66 = {-1,-1,-1,+4,-1,-1,-1,-1,-1};
//	int[] row77 = {-1,-1,+3,-1,-1,-1,-1,-1,-1};
//	int[] row88 = {-1,+2,-1,-1,-1,-1,-1,-1,-1};
//	int[] row99 = {+1,-1,-1,-1,-1,-1,-1,-1,-1};
}


