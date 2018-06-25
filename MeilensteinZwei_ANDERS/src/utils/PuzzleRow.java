package utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import data.Grid;

public class PuzzleRow implements RowSortable{
	
	//TEST CHANGE
	
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

	public boolean hasRowConflictFree(Grid grid) {
		// TODO Auto-generated method stub
		return false;
	}

	public Grid getRowSortColBlock(Grid grid) {
		// TODO Auto-generated method stub
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
	
	static LinkedList[] allPerm = new LinkedList[9*8*7*6*5*4*3*2];
	static int allPermInd = 0;
	
	/**
	 * Hilfsmethode zu getRowConflictFree.
	 * Überprüft auf dem Sudoku, ob durch eine neue Anordnung der Zeilen, ein v-konfliktfreies Sudoku entstehen kann.
	 * @param grid Sudoku, auf dem die Zeilen neu angeordnet werden, um eine mögliche v-konfliktfreie Anordnung zu finden.
	 * @return grid Eine v-konfliktfreie Anordnung. Falls keine solche existiert, ein Nullergrid.
	 */
	private Grid changeAll(Grid grid) {
		Grid temp = new Grid(9);
		
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
	    
	    System.out.println(allPerm.toString());
	    
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
	
	public static void permutation(Set<Integer> möglicheNummer, Stack<Integer> permutation, int size) {

	    if(permutation.size() == size) {
	    	LinkedList<Integer> temp = new LinkedList<Integer>();
	    	
	    	int k = 0;
	    	for(int i = 0; i < permutation.size(); i++){
	    		temp.add(k, permutation.get(i));
	    		k++;
	    	}
	    	allPerm[allPermInd++] = temp;
//	    	System.out.println(Arrays.toString(permutation.toArray(new Integer[0])));
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
	
	public void auslesen(int[] a){
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i] + ",");
		}
		System.out.println("");
	}
	

}
