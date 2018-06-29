package utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

import data.Cell;
import data.Grid;

public class PuzzleRow implements RowSortable{
	
	@SuppressWarnings("rawtypes")
	static LinkedList[] allPerm = new LinkedList[9*8*7*6*5*4*3*2];
	static int allPermInd = 0;
	
	/**
	 * Konstruktor der Klasse. Bewirkt, dass die Methode permuteAll() bei Erzeugung eines Objektes dieser Klasse aufgerufen wird.
	 */
	public PuzzleRow(){
		permuteAll();
	}
	
	/**
	 * Hilfsmethode zu der gesamten Klasse.
	 * Erstellt das Set s und rift mit diesem die SubMethode permutation auf
	 */
	public static void permuteAll(){
		Set<Integer> s = new HashSet<Integer>();
	    s.add(1);
	    s.add(2);
	    s.add(3);
	    s.add(4);
	    s.add(5);
	    s.add(6);
	    s.add(7);
	    s.add(8);
	    s.add(9);
	    
	    permutationAll(s, new Stack<Integer>(), s.size());
	}
	
	/**
	 * Submethode zu permute().
	 * Erstellt alle Permutationen aus der Reihe 1-9 und speichert diese in die globale LinkedListe allPerm
	 * @param moeglicheNummer die Reihe aus der eine Permutation erstellt werden soll.
	 * @param permutation eine der vielen Permutationen der Reihe.
	 * @param size Groesse, des moeglicheNummer Stacks; dient als "speicher-Bedingung".
	 */
	public static void permutationAll(Set<Integer> moeglicheNummer, Stack<Integer> permutation, int size) {

	    if(permutation.size() == size) {
	    	LinkedList<Integer> temp = new LinkedList<Integer>();
	    	
	    	int k = 0;
	    	for(int i = 0; i < permutation.size(); i++){
	    		temp.add(k, permutation.get(i));
	    		k++;
	    	}
	    	allPerm[allPermInd++] = temp;
	    }

	    Integer[] zahlen = moeglicheNummer.toArray(new Integer[0]);
	    
	    for(Integer i : zahlen) {
	        permutation.push(i);
	        moeglicheNummer.remove(i);
	        permutationAll(moeglicheNummer, permutation, size);
	        moeglicheNummer.add(permutation.pop());
	    }
	}
	
	/**
	 * Ueberprueft, ob es mindestens eine v-konfliktfreie Anordnung der gegebenen Zeilen gibt.
	 * @param grid Sudoku, bei dem jede der neun Zeilen komplett mit 1-9 belegt sind.
	 * @return grid Eine v-konfliktfreie Anordnung. Falls keine solche existiert, ein Nullergrid.
	 */
	public Grid getRowConflictFree(Grid grid) {
		
		if(isConflictFree(grid) == true){
			return grid;
		}else{
			Grid temp = changeAll(grid, 1);
			
			if(temp == null){
				return returnNullGrid();
			}else{
				return temp;
			}
		}
	}

	/**
	 * Ueberprueft, ob es mindestens eine v-konfliktfreie Anordnung der gegebenen Zeilen gibt.
	 * @param grid Sudoku, bei dem jede der neun Zeilen komplett mit 1-9 belegt sind.
	 * @return boolean true, falls es eine v-konfliktfreie Anordnung gibt, false falls nicht.
	 */
	public boolean hasRowConflictFree(Grid grid) {
		Grid temp = getRowConflictFree(grid);
		
		if(isNullGrid(temp) == true){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * Ueberprueft, ob es mindestens eine Anordnung gibt, bei der in jeder Spalte und jedem Block
	 * die Zahlen von 1-9 genau einmal vorkommen..
	 * @param grid Sudoku, bei dem jede der neun Zeilen komplett mit 1-9 belegt sind.
	 * @return grid Es wird das input grid zurueckgegeben, falls die Bedingungen von vorne herein erfuellt sind.
	 * Es wird temp zurueckgegeben, falls durch Tauschoperationen, eine Bedingung Erfuellende Konstellation gefunden wurde.
	 * Es wird ein NullGrid zurueckgegeben, falls es keine solche Konstellation gibt.
	 */
	public Grid getRowSortColBlock(Grid grid) {
		if(colValid(grid) == true && blockValid(grid) == true){
			return grid;
		}else{
			Grid temp = changeAll(grid, 2);			
			if(temp == null){
				return returnNullGrid();
			}else if(temp != null){
				return temp;
			}
		}
		return null;
	}

	/**
	 * Ueberprueft, ob es mindestens eine Anordnung gibt, bei der in jeder Spalte und jedem Block
	 * die Zahlen von 1-9 genau einmal vorkommen..
	 * @param grid Sudoku, bei dem jede der neun Zeilen komplett mit 1-9 belegt sind.
	 * @return boolean true, falls es eine solche Anordnung gibt. false, falls nicht.
	 */
	public boolean hasRowSortColBlock(Grid grid) {
		Grid temp = getRowSortColBlock(grid);
		
		if(isNullGrid(temp) == true){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * Ueberprueft, ob es mindestens eine Anordnung gibt, ein zulaessig gefuelltes Sudoku zu erhalten.
	 * @param grid Sudoku, bei dem jede der neun Zeilen komplett mit 1-9 belegt sind.
	 * @return grid falls das Input Sudoku bereits zulaessig gefuellt ist.
	 * temp falls durch eine neue Anordnung der Rows ein zulaessig gefuelltes Sudoku entstand.
	 * nullGrid falls es keine solche Anordnung der Reihen gibt.
	 */
	public Grid getRowSudoku(Grid grid) {
		if(colValid(grid) == true && blockValid(grid) == true && rowValid(grid) == true){
			return grid;
		}else{
			Grid temp = changeAll(grid, 3);			
			if(temp == null){
				return returnNullGrid();
			}else if(temp != null){
				return temp;
			}
		}		
		return null;
	}

	/**
	 * Ueberprueft, ob es mindestens eine Anordnung gibt, ein zulaessig gefuelltes Sudoku zu erhalten.
	 * @param grid Sudoku, bei dem jede der neun Zeilen komplett mit 1-9 belegt sind.
	 * @return boolean true, falls es eine solche Anordnung gibt. false, falls nicht.
	 */
	public boolean hasRowSudoku(Grid grid) {
		Grid temp = getRowSudoku(grid);
		
		if(isNullGrid(temp) == true){
			return false;
		}else{
			return true;
		}
	}

	public int putNumberColBlock(Grid grid, int number) {
		if(isPartyFilled(grid) == false){
			return 0;
		}else{
			LinkedList<Cell> possibleCells = getPossibleCells(grid, number);
			
			
			return 99;
		}
	}
	
	public LinkedList<Cell> getPossibleCells(Grid grid, int number){
		LinkedList<Cell> possibleCells = new LinkedList<Cell>();
		LinkedList<Cell> blocks = blocksWithoutNumber(grid, number);
	
		for(int blocksInd = 0; blocksInd < blocks.size(); blocksInd++){
			
			int blockCol = blocks.get(blocksInd).getcIndex() - ((blocks.get(blocksInd).getcIndex()-1)%3);
			int blockRow = blocks.get(blocksInd).getrIndex() - ((blocks.get(blocksInd).getrIndex()-1)%3);
			
			for(int i = blockRow; i < blockRow+3; i++){
				for(int j = blockCol; j < blockCol+3; j++){
					
					if(possibleForNumber(grid, grid.getCell(i, j), number) == true){
						possibleCells.add(grid.getCell(i, j));
					}
				}
			}
		}
		
		return possibleCells;
	}
	
	public LinkedList<Cell> blocksWithoutNumber(Grid grid, int number){
		LinkedList<Cell> allBlocks = new LinkedList<Cell>();
		allBlocks.add(grid.getCell(1, 1));
		allBlocks.add(grid.getCell(1, 4));
		allBlocks.add(grid.getCell(1, 7));
		allBlocks.add(grid.getCell(4, 1));
		allBlocks.add(grid.getCell(4, 4));
		allBlocks.add(grid.getCell(4, 7));
		allBlocks.add(grid.getCell(7, 1));
		allBlocks.add(grid.getCell(7, 4));
		allBlocks.add(grid.getCell(7, 7));
		
		LinkedList<Cell> temp = new LinkedList<Cell>();
		
		for(int ABInd = 0; ABInd < allBlocks.size(); ABInd++){
				
			int blockCol = (allBlocks.get(ABInd).getcIndex() - ((allBlocks.get(ABInd).getcIndex()-1)%3));
			int blockRow = (allBlocks.get(ABInd).getrIndex() - ((allBlocks.get(ABInd).getrIndex()-1)%3));
			Cell tempBlockAnchor = new Cell(blockRow, blockCol);
			int dontSaveBlock = 0;
			
			for(int i = blockRow; i < blockRow+3; i++){
				for(int j = blockCol; j < blockCol+3; j++){	
					
					if(grid.getValue(i, j) == number){
						dontSaveBlock++;
					}
				}
			}
			
			if(dontSaveBlock == 0){
					temp.add(tempBlockAnchor);
			}
		}
		return temp;
	}

	public boolean possibleForNumber(Grid grid, Cell a, int number){
		int nope = 0;
		
		for(int i = 0; i < 9; i++){
			if(grid.getColValues(a.getcIndex())[i] == number){
				nope++;
			}
		}
		
		if(a.getValue() == -1 && nope == 0){
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * Hilfsmethode zu hasRowConflictFree.
	 * Ueberprueft auf dem grid, ob es nur aus Nullen besteht - also ein Nullergrid ist.
	 * @param grid Sudoku, auf dem die Anzahl der Nullen ermittelt wird.
	 * @return boolean Besteht das Sudoku nur aus Nullen, so wird true zurueckgegeben. Sonst false.
	 */
	public boolean isNullGrid(Grid temp) {
		int nullCounter = 0;
		
		int row = 1;
		while(row < 10){
			for(int rIndex = 0; rIndex < temp.getRowValues(row).length; rIndex++){
				if(temp.getRowValues(row)[rIndex] == 0){
					nullCounter++;
				}
			}
			row++;
		}
		
		if(nullCounter == 81){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Hilfsmethode zu getRowConflictFree, getRowSortColBlock, getRowSudoku.
	 * Ueberprueft, bei call = 1, auf dem Sudoku, ob durch eine neue Anordnung der Zeilen, ein v-konfliktfreies Sudoku entstehen kann.
	 * Ueberprueft, bei call = 2, auf dem Sudoku, ob durch eine neue Anordnung der Zeilen, ein Sudoku entsteht bei dem in jeder Spalte, Block die Zahlen von 1-9 genau einmal vorkommen.
	 * Ueberprueft, bei call = 3, auf dem Sudoku, ob durch eine neue Anordnung ein zulaessig gefuelltes Sudoku entstehen kann.
	 * @param grid Sudoku, auf dem die Zeilen neu angeordnet werden, um eine moegliche v-konfliktfreie Anordnung zu finden.
	 * @param call 1, falls von getRowConflictFree aufgerufen. 2, falls von getRowSortConflictFree aufgerufen. 3, falls von getRowSudoku aufgerufen.
	 * @return grid Eine v-konfliktfreie Anordnung. Falls keine solche existiert, ein Nullergrid.
	 */
	public Grid changeAll(Grid grid, int call) {
		Grid temp = new Grid(9);
	    
	    if(allPerm != null){
		    for(int allPermInd = 0; allPermInd < allPerm.length; allPermInd++){		    	
	    		for(int rInd = 1; rInd < 10; rInd++){
		    		temp.setRowValues(rInd, grid.getRowValues((int) (allPerm[allPermInd]).get(rInd-1)));
		    	}
	    		
	    		if(call == 1){
	    			if(isConflictFree(temp) == true){
	    				return temp;
	    			}
	    		}else if(call == 2){
	    			if(colValid(temp) == true && blockValid(temp) == true){
			    		return temp;
			    	}
	    		}else if(call == 3){
	    			if(colValid(temp) == true && blockValid(temp) == true && rowValid(temp) == true){
			    		return temp;
			    	}
	    		}
		    }
	    }
		return null;
	}

	/**
	 * Hilfsmethode zu getRowConflictFree.
	 * Ueberprueft, ob das gegebene Grid nicht schon v-konfliktfrei ist.
	 * @param grid Das Grid, auf dem geprueft wird, ob es bereits v-konfliktfrei ist.
	 * @return boolean true, falls v-konfliktfrei. false, falls nicht.
	 */
	public boolean isConflictFree(Grid grid) {
		for(int row = 1; row < grid.getRowValues(1).length; row++){
			for(int col = 1; col < grid.getRowValues(1).length; col++){
				
				if(grid.getValue(row, col) == grid.getValue(row+1, col) && grid.getValue(row, col) != -1){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Erzeugt ein Grid und fuellt es mit Nullen.
	 * @return grid Das mit Nullen befuellte Sudoku bzw Grid
	 */
	public Grid returnNullGrid(){
		Grid nullGrid = new Grid(9);
		int[] nullRow = {0,0,0,0,0,0,0,0,0};
		
		for(int rIndex = 1; rIndex < nullGrid.getRowValues(1).length+1; rIndex++){
			nullGrid.setRowValues(rIndex, nullRow);
		}		
		return nullGrid;
	}

	/**
	 * Hilfsmethode zu der Methode getRowSortColBlock.
	 * Ueberprueft alle Columns, ob in ihnen jede Zahl jeweils einmal vorkommt.
	 * @param grid Das Sudoku auf dem geprueft wird
	 * @return boolean true, falls jede Zahl in jeder Col einmal vorkommt. false, falls nicht.
	 */
	public boolean colValid(Grid grid){
		int wrong = 0;
		
		for(int col = 0; col < grid.getRowValues(1).length; col++){
			int[] numbers = {0,0,0,0,0,0,0,0,0};
			
			for(int row = 0; row < grid.getColValues(1).length; row++){
				numbers[(grid.getValue(row+1, col+1))-1]++;
			}
			
			for(int i = 0; i < numbers.length; i++){
				if(numbers[i] != 1){
					wrong++;
				}
			}
		}		
		if(wrong > 0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Hilfsmethode zu der Methode getRowSudoku.
	 * Ueberprueft alle Rows, ob in ihnen jede Zahl jeweils einmal vorkommt.
	 * @param grid Das Sudoku auf dem geprueft wird
	 * @return boolean true, falls jede Zahl in jeder Row einmal vorkommt. false, falls nicht.
	 */
	public boolean rowValid(Grid grid){
		int wrong = 0;
		
		for(int row = 0; row < grid.getColValues(1).length; row++){
			int[] numbers = {0,0,0,0,0,0,0,0,0};
			
			for(int col = 0; col < grid.getRowValues(1).length; col++){
				numbers[(grid.getValue(row+1, col+1))-1]++;
			}
			
			for(int i = 0; i < numbers.length; i++){
				if(numbers[i] != 1){
					wrong++;
				}
			}
		}		
		if(wrong > 0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Hilfsmethode zu der Methode getRowSortColBlock.
	 * Ueberprueft alle Blocks, ob in ihnen jede Zahl jeweils einmal vorkommt.
	 * @param grid Das Sudoku auf dem geprueft wird
	 * @return boolean true, falls jede Zahl in jedem Block einmal vorkommt. false, falls nicht.
	 */
	public boolean blockValid(Grid grid){
		int nope = 0;
		
		for(int i = 1; i < 10; i = i+3){
			for(int j = 1; j < 10; j = j+3){
				nope = nope + subBlockValid(grid, grid.getCell(i, j));
			}
		}
		
		if(nope != 0){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * Submethode zu der Methode blockValid.
	 * Ueber die Ankerzelle, die uebergeben wird, wird der gesamte Block ermittelt und durchlaufen
	 * @param grid Das Sudoku auf dem geprueft wird
	 * @param a Ankerzelle eines Blocks
	 * @return int 1 falls eine Zahl mehr als einmal pro BLock vorkommt. 0 falls nicht.
	 */
	public int subBlockValid(Grid grid, Cell a){
		int blockCol = (a.getcIndex() - ((a.getcIndex()-1)%3));
		int blockRow = (a.getrIndex() - ((a.getrIndex()-1)%3));
		int[] numbers = {0,0,0,0,0,0,0,0,0};
		
		for(int i = blockRow; i < blockRow+3; i++){
			for(int j = blockCol; j < blockCol+3; j++){	
				
				numbers[(grid.getValue(i, j))-1]++;
			}
		}
		
		int nope = 0;
		for(int i = 0; i < numbers.length; i++){
			if(numbers[i] != 1){
				nope++;
			}
		}
		
		if(nope != 0){
			return 1;
		}else{
			return 0;
		}
	}

	public boolean isPartyFilled(Grid grid){
		int minCounter = 0;
		
		for(int row = 1; row < 10; row++){
			for(int col = 1; col < 10; col++){
				
				if(grid.getCell(row, col).getValue() == -1){
					minCounter++;
				}
			}
		}
		
		if(minCounter == 0){
			return false;
		}else{
			return true;
		}
	}
	
	public void auslesen(int[] a){
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i] + ",");
		}
		System.out.println("");
	}
	

}
