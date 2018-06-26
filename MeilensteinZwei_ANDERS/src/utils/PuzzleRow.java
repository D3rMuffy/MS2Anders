package utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

import data.Cell;
import data.Grid;

public class PuzzleRow implements RowSortable{
	
	public PuzzleRow(){
		permute();
	}
	
	/**
	 * Überprüft, ob es midnestens eine v-konfliktfreie Anordnung der gegebenen Zeilen gibt.
	 * @param grid Sudoku, bei dem jede der neun Zeilen komplett mit 1-9 belegt sind.
	 * @return grid Eine v-konfliktfreie Anordnung. Falls keine solche existiert, ein Nullergrid.
	 */
	public Grid getRowConflictFree(Grid grid) {
		
		if(isConflictFree(grid) == true){
			return grid;
		}else{
			Grid temp = changeAll(grid);
			
			if(temp == null){
				return returnNullGrid();
			}else{
				return temp;
			}
		}
	}

	/**
	 * Überprüft, ob es midnestens eine v-konfliktfreie Anordnung der gegebenen Zeilen gibt.
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

	public Grid getRowSortColBlock(Grid grid) {
		if(colValid(grid) == true && blockValid(grid) == true){
			return grid;
		}else{
			System.out.println("PROKEMN");
		}
		return null;
	}

	public boolean hasRowSortColBlock(Grid grid) {
		// TODO Auto-generated method stub
		return false;
	}

	public Grid getRowSudoku(Grid grid) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasRowSudoku(Grid grid) {
		// TODO Auto-generated method stub
		return false;
	}

	public int putNumberColBlock(Grid grid, int number) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@SuppressWarnings("rawtypes")
	static LinkedList[] allPerm = new LinkedList[9*8*7*6*5*4*3*2];
	static int allPermInd = 0;
	
	/**
	 * Hilfsmethode zu hasRowConflictFree.
	 * Überprüft auf dem grid, ob es nur aus Nullen besteht - also ein Nullergrid ist.
	 * @param grid Sudoku, auf dem die Anzahl der Nullen ermittelt wird.
	 * @return boolean Besteht das Sudoku nur aus Nullen, so wird true zurückgegeben. Sonst false.
	 */
	private boolean isNullGrid(Grid temp) {
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
	 * Hilfsmethode zu getRowConflictFree.
	 * Überprüft auf dem Sudoku, ob durch eine neue Anordnung der Zeilen, ein v-konfliktfreies Sudoku entstehen kann.
	 * @param grid Sudoku, auf dem die Zeilen neu angeordnet werden, um eine mögliche v-konfliktfreie Anordnung zu finden.
	 * @return grid Eine v-konfliktfreie Anordnung. Falls keine solche existiert, ein Nullergrid.
	 */
	private Grid changeAll(Grid grid) {
		Grid temp = new Grid(9);
	    
	    if(allPerm != null){
		    for(int allPermInd = 0; allPermInd < allPerm.length; allPermInd++){		    	
	    		for(int rInd = 1; rInd < 10; rInd++){
		    		temp.setRowValues(rInd, grid.getRowValues((int) (allPerm[allPermInd]).get(rInd-1)));
		    	}
		    	if(isConflictFree(temp) == true){
		    		return temp;
		    	}
		    }
	    }
		return null;
	}

	/**
	 * Hilfsmethode zu getRowConflictFree.
	 * Überprüft, ob das gegebene Grid nicht schon v-konfliktfrei ist.
	 * @param grid Das Grid, auf dem geprüft wird, ob es bereits v-konfliktfrei ist.
	 * @return boolean true, falls v-konfliktfrei. false, falls nicht.
	 */
	private boolean isConflictFree(Grid grid) {
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
	 * Erzeugt ein Grid und füllt es mit Nullen.
	 * @return grid Das mit Nullen befüllte Sudoku bzw Grid
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
	 * Hilfsmethode zu der gesamten Klasse.
	 * Erstellt das Set s und rift mit diesem die SubMethode permutation auf
	 */
	public static void permute(){
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
	    
	    permutation(s, new Stack<Integer>(), s.size());
	}
	
	/**
	 * Submethode zu permute().
	 * Erstellt alle Permutationen aus der Reihe 1-9 und speichert diese in die globale LinkedListe allPerm
	 * @param möglicheNummer die Reihe aus der eine Permutation erstellt werden soll.
	 * @param permutation eine der vielen Permutationen der Reihe.
	 * @param size Größe, des möglicheNummer Stacks; dient als "speicher-Bedingung".
	 */
	public static void permutation(Set<Integer> möglicheNummer, Stack<Integer> permutation, int size) {

	    if(permutation.size() == size) {
	    	LinkedList<Integer> temp = new LinkedList<Integer>();
	    	
	    	int k = 0;
	    	for(int i = 0; i < permutation.size(); i++){
	    		temp.add(k, permutation.get(i));
	    		k++;
	    	}
	    	allPerm[allPermInd++] = temp;
	    }

	    Integer[] zahlen = möglicheNummer.toArray(new Integer[0]);
	    
	    for(Integer i : zahlen) {
	        permutation.push(i);
	        möglicheNummer.remove(i);
	        permutation(möglicheNummer, permutation, size);
	        möglicheNummer.add(permutation.pop());
	    }
	}
	
	/**
	 * Hilfsmethode zu der Methode getRowSortColBlock.
	 * Überprüft alle Columns, ob in ihnen jede Zahl jeweils einmal vorkommt.
	 * @param grid Das Sudoku auf dem geprüft wird
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
	 * Hilfsmethode zu der Methode getRowSortColBlock.
	 * Überprüft alle Blocks, ob in ihnen jede Zahl jeweils einmal vorkommt.
	 * @param grid Das Sudoku auf dem geprüft wird
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
	 * Über die Ankerzelle, die übergeben wird, wird der gesamte Block ermittelt und durchlaufen
	 * @param grid Das Sudoku auf dem geprüft wird
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
	
	public void auslesen(int[] a){
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i] + ",");
		}
		System.out.println("");
	}
	

}
