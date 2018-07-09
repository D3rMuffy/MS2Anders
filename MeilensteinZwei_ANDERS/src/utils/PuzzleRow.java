package utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

import data.Cell;
import data.Grid;


/**
 * @author Teichmeister, Christoph
 */
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
	 * 
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
	 * 
	 * @param grid Sudoku, bei dem jede der neun Zeilen komplett mit 1-9 belegt sind.
	 * @return grid Eine v-konfliktfreie Anordnung. Falls keine solche existiert, ein Nullergrid.
	 */
	public Grid getRowConflictFree(Grid grid) {
		
		if(isPartyFilled(grid) == false) {
			if (isConflictFree(grid) == true) {
				return grid;
			} else {
				Grid temp = changeAll(grid, 1);

				if (temp == null) {
					return returnNullGrid();
				} else {
					return temp;
				}
			} 
		}else{
			return returnNullGrid();
		}
	}

	/**
	 * Ueberprueft, ob es mindestens eine v-konfliktfreie Anordnung der gegebenen Zeilen gibt.
	 * 
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
	 * 
	 * @param grid Sudoku, bei dem jede der neun Zeilen komplett mit 1-9 belegt sind.
	 * @return grid Es wird das input grid zurueckgegeben, falls die Bedingungen von vorne herein erfuellt sind.
	 * Es wird temp zurueckgegeben, falls durch Tauschoperationen, eine Bedingung Erfuellende Konstellation gefunden wurde.
	 * Es wird ein NullGrid zurueckgegeben, falls es keine solche Konstellation gibt.
	 */
	public Grid getRowSortColBlock(Grid grid) {
		if(isPartyFilled(grid) == false){
			if (colValid(grid) == true && blockValid(grid) == true) {
				return grid;
			} else {
				Grid temp = changeAll(grid, 2);
				if (temp == null) {
					return returnNullGrid();
				} else if (temp != null) {
					return temp;
				}
			} 
		}else{
			return returnNullGrid();
		}
		return null;
	}

	/**
	 * Ueberprueft, ob es mindestens eine Anordnung gibt, bei der in jeder Spalte und jedem Block
	 * die Zahlen von 1-9 genau einmal vorkommen..
	 * 
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
	 * 
	 * @param grid Sudoku, bei dem jede der neun Zeilen komplett mit 1-9 belegt sind.
	 * @return grid falls das Input Sudoku bereits zulaessig gefuellt ist.
	 * temp falls durch eine neue Anordnung der Rows ein zulaessig gefuelltes Sudoku entstand.
	 * nullGrid falls es keine solche Anordnung der Reihen gibt.
	 */
	public Grid getRowSudoku(Grid grid){
		if(isPartyFilled(grid) == false) {
			
			if (colValid(grid) == true && blockValid(grid) == true && rowValid(grid) == true) {
				return grid;
			} else {
				Grid temp = changeAll(grid, 3);
				if (temp == null) {
					return returnNullGrid();
				} else if (temp != null) {
					return temp;
				}
			} 
		}else{
			return returnNullGrid();
		}
		return null;
	}

	/**
	 * Ueberprueft, ob es mindestens eine Anordnung gibt, ein zulaessig gefuelltes Sudoku zu erhalten.
	 * 
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

	/**
	 * Zaehlt die moeglichen verschiedenen number-Anordnungen, und gibt die Anzahl wieder.
	 * 
	 * @param grid Sudoku, welches teilbefuellt ist.
	 * @param number Zahl, dessen Erweiterungen gezaehlt werden sollen.
	 * @return int Die Anzahl der number-Anordnungen.
	 */
	public int putNumberColBlock(Grid grid, int number) {
		if(isPartyFilled(grid) == false){
			return 0;
		}else{
			if(rulesConfirmed(grid, number) == true){
				if(isEmptyGrid(grid) == true){
					return 4251528;
				}else{
					LinkedList[] columns = sortFixedAndPossible(grid, number);
					
					startRecursion(grid, number, columns);
					return counter;
				}
			}else{
				return 0;
			}
		}
	}
	
	/**
	 * Submethode zu der Methode putNumberColBlock.
	 * Durchlaeuft das LinkedList Array mit den sortierten Zellen bezueglich der Number Belegungs Moeglichkeiten und
	 * ruft mit den maeoeglichen Zellen die rekursive Suche nach den folgenden Zellbelegungsmoeglichkeiten auf.
	 * 
	 * @param grid Das Sudoku auf dem die Zellen ermittelt werden
	 * @param number Die Zahl deren Erweiterungen ermittelt werden sollen.
	 * @param columns Das LinkedList Array auf dem die Zellen, die entweder mit number belegt sind oder fuer number moeglich waeren gespeichert sind.
	 */
	public void startRecursion(Grid grid, int number, LinkedList[] columns){
		for(int i = 0; i < columns.length; i++){
			if(columns[i].size() > 1){
				
				for(int j = 0; j < columns[i].size(); j++){
					emptyWay();
					cellWay.add((Cell) columns[i].get(j));
					recursion(grid, number, i, j,  columns);
				}
				break;
			}else{
				Cell a = (Cell) columns[i].get(0);
				cellWay.add(a);
			}
			
		}
	}
	
	LinkedList<Cell> cellWay = new LinkedList<Cell>();
	int counter = 0;
	LinkedList<LinkedList> allCellWays = new LinkedList<LinkedList>();
	
	/**
	 * Submethode zu der Methode startRecursion.
	 * Waehlt die Zelle aus, die in der aktuellen Mmoeglichen Belegung gewaehlt werden soll und speichert diese in der globalen LinkedList cellWay.
	 * Ueberprueft bei der Auswahl der naechsten Zelle natuerlich auch jedesmal, ob die Voraussetzungen noch erfuellt sind.
	 * 
	 * @param grid Das Sudoku auf dem die Zellen ermittelt werden
	 * @param number Die Zahl deren Erweiterungen ermittelt werden sollen.
	 * @param col Der Index des LinkedList Array column, auf dem wir uns gerade befinden.
	 * @param row Der Index der LinkedList mit den moeglichen Zellen fuer number in dem column Array, auf dem wir uns befinden, oder dass wir ueberpruefen wollen.
	 * @param columns Das LinkedList Array auf dem die Zellen, die entweder mit number belegt sind oder fuer number moeglich waeren gespeichert sind.
	 */
	public void recursion(Grid grid, int number, int col, int row, LinkedList[] columns){

		if(col == 0){
			if(columns[col+1].size() > 1){
			
				for(int i = 0; i < columns[col+1].size(); i++){
					if(doesntViolate((Cell) columns[col+1].get(i), (Cell) columns[col].get(row))	== true){
						Cell c = (Cell) columns[col+1].get(i);
						cellWay.add(c);
						recursion(grid, number, col+1, i, columns);
					}
				}
			}else if(columns[col+1].size() == 1){
				Cell fix = (Cell) columns[col+1].get(0);
				cellWay.add(fix);
				recursion(grid, number, col+1, (fix.getrIndex()-1), columns);
			}
		}else if(col == 1){
			
			if(columns[col+1].size() == 1){
				Cell fix = (Cell) columns[col+1].get(0);
				cellWay.add(fix);
				recursion(grid, number, col+1, (fix.getrIndex()-1), columns);
				
			}else if(columns[col+1].size() > 1){
				Cell b = cellWay.get(cellWay.size()-1);
				Cell c = null;
				
				for(int i = 0; i < columns[col+1].size(); i++){
					c = (Cell) columns[col+1].get(i);
					if(compare(b, c) == true){
						cellWay.add(c);
						recursion(grid, number, col+1, i, columns);
					}
				}
			}
			
			
		}else if(col < 8 && col > 1){
			if(columns[col+1].size() == 1){
				Cell fix = (Cell) columns[col+1].get(0);
				cellWay.add(fix);
				recursion(grid, number, col+1, (fix.getrIndex()-1), columns);
				
			}else if(columns[col+1].size() > 1){
				Cell a = cellWay.get(cellWay.size()-2);
				Cell b = cellWay.get(cellWay.size()-1);
				Cell c = null;
				
				for(int i = 0; i < columns[col+1].size(); i++){
					c = (Cell) columns[col+1].get(i);
					if(compare(a, b, c) == true){
						cellWay.add(c);
						recursion(grid, number, col+1, i, columns);
					}
					
				}
				
			}
		
		}else if(col == 8){
			if(columns[col].size() == 1){
				Cell fix = (Cell) columns[col].get(0);
				cellWay.add(fix);
				counter++;
				LinkedList<Cell> a = (LinkedList<Cell>) cellWay.clone();
				allCellWays.add(a);
				
			}else if(columns[col].size() > 1){
				Cell a = cellWay.get(cellWay.size()-2);
				Cell b = cellWay.get(cellWay.size()-1);
				Cell c = null;
				int cellRow = 0;
				
				for(int i = 0; i < columns[col].size(); i++){
					c = (Cell) columns[col].get(i);
					if(compare(a, b, c) == true){
						cellWay.add(c);
						counter++;
						break;
					}
					
				}
				LinkedList<Cell> d = (LinkedList<Cell>) cellWay.clone();
				allCellWays.add(d);
			}
		}
	}
	
	/**
	 * Submethode zu der Methode putNumberColBlock..
	 * Ueberprueft, ob die Voraussetzungen der Methode auf die zwei uebergebenen Zellen zustimmen.
	 * 
	 * @param a "Vorherige" Zelle
	 * @param b Aktuell gesuchte Zelle
	 * @return boolean true falls die Voraussetzungen erfuellt sind. False, falls nicht.
	 */
	public boolean doesntViolate(Cell a, Cell b){
		int blockRowA = a.getrIndex() - ((a.getrIndex()-1)%3);
		int blockColA = a.getcIndex() - ((a.getcIndex()-1)%3);
		
		int blockRowB = b.getrIndex() - ((b.getrIndex()-1)%3);
		int blockColB = b.getcIndex() - ((b.getcIndex()-1)%3);
		
		if(blockRowA == blockRowB && blockColA == blockColB){
			return false;
		}else if(a.getcIndex() == b.getcIndex()){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Submethode zu der Methode recursion.
	 * Bekommt die aktuelle und letzt-gewaehlte Zelle und prueft, dass diese nicht im selben Block liegen, da sonst eine Verletzung
	 * der Voraussetzung eintreten wuerde.
	 * 
	 * @param b Letzt-gewaehlte Zelle .
	 * @param c Zelle die aktuell geprueft wird.
	 * @return true, falls kein Verstoss vorliegt. False, falls doch.
	 */
	public boolean compare (Cell b, Cell c){
		int bCol = b.getcIndex() - ((b.getcIndex()-1)%3);
		int bRow = b.getrIndex() - ((b.getrIndex()-1)%3);
		
		int cCol = c.getcIndex() - ((c.getcIndex()-1)%3);
		int cRow = c.getrIndex() - ((c.getrIndex()-1)%3);
		
		if(cCol == bCol && cRow == bRow){
			return false;
		}
		return true;
	}
	
	/**
	 * Submethode zu der Methode recursion.
	 * Bekommt die aktuelle, letzt-gewaehlte und vborletzt-gewaehlte Zelle und prueft, dass diese nicht im selben Block liegen, da sonst eine Verletzung
	 * der Voraussetzung eintreten wuerde.
	 * 
	 * @param a vorletzt-gewaehlte Zelle.
	 * @param b Letzt-gewaehlte Zelle.
	 * @param c Zelle die aktuell geprueft wird.
	 * @return true, falls kein Verstoss vorliegt. False, falls doch.
	 */
	public boolean compare(Cell a, Cell b, Cell c){
		int aCol = a.getcIndex() - ((a.getcIndex()-1)%3);
		int aRow = a.getrIndex() - ((a.getrIndex()-1)%3);
		
		int bCol = b.getcIndex() - ((b.getcIndex()-1)%3);
		int bRow = b.getrIndex() - ((b.getrIndex()-1)%3);
		
		int cCol = c.getcIndex() - ((c.getcIndex()-1)%3);
		int cRow = c.getrIndex() - ((c.getrIndex()-1)%3);
		
		if(cCol == aCol && cRow == aRow){
			return false;
		}
		if(cCol == bCol && cRow == bRow){
			return false;
		}
		return true;
	}
	
	/**
	 * Submethode zu der Methode startRecursion.
	 *Entleert die globale LinkedList cellWay, auf der die Zellen gespeichert sind, die wir bei der aktuellen Ermittlung einer
	 *validen Belegung abgelaufen sind.
	 */
	public void emptyWay(){
		while(cellWay.isEmpty() == false){
			cellWay.removeFirst();
		}
	}
	
	/**
	 * Submethode zu der Methode putNumberColBlock.
	 * Ordnet und sortiert die fuer die Number moeglichen Zellen und die Zellen in denen Number schon drinnen steht,
	 * in einem LinkedList Array.
	 * 
	 * @param grid Das Sudoku auf dem die Zellen ermittelt werden
	 * @param number Die Zahl deren Erweiterungen ermittelt werden sollen.
	 * @return Columns Ein LinkedList Array, auf dem die Zellen die fuer Number moeglich sind, oder Number enthalten ist, gespeichert sind.
	 * Hier steht der Index des Arrays fuer die jeweilige Column im Grid.
	 */
	public LinkedList[] sortFixedAndPossible(Grid grid, int number){
		LinkedList<Cell> possibleCells = getPossibleCells(grid, number);
		LinkedList<Cell> fixedCells = getFixedCells(grid, number);
		
		LinkedList[] Columns = new LinkedList[9];
		for(int i = 0; i < Columns.length; i++){
			LinkedList<Cell> cellPerColumn = new LinkedList<Cell>();
			Columns[i] = cellPerColumn;
		}
		
		for(int i = 0; i < possibleCells.size(); i++){
			Columns[possibleCells.get(i).getcIndex()-1].add(possibleCells.get(i));
		}
		
		for(int i = 0; i < fixedCells.size(); i++){
			Columns[fixedCells.get(i).getcIndex()-1].add(fixedCells.get(i));
		}
		
		return Columns;
	}

	/**
	 * Submethode zu der Methode sortFixedAndPossible.
	 * Ordnet und sortiert die Zellen in denen Number schon drinnen steht in einer LinkedList.
	 * 
	 * @param grid Das Sudoku auf dem die Zellen ermittelt werden
	 * @param number Die Zahl deren Erweiterungen ermittelt werden sollen.
	 * @return fixedCells Eine LinkedList, auf dem die Zellen wo Number enthalten ist, gespeichert sind.
	 */
	public LinkedList<Cell> getFixedCells(Grid grid, int number){
		LinkedList<Cell> fixedCells = new LinkedList<Cell>();
		
		for(int i = 1; i < 10; i++){
			for(int j = 1; j < 10; j++){
				
				if(grid.getValue(i, j) == number){
					fixedCells.add(grid.getCell(i, j));
				}
			}
		}
		return fixedCells;
	}

	/**
	 * Submethode zu der Methode sortFixedAndPossible.
	 * Ordnet und sortiert die Zellen in denen Number stehen koennte in einer LinkedList.
	 * 
	 * @param grid Das Sudoku auf dem die Zellen ermittelt werden
	 * @param number Die Zahl deren Erweiterungen ermittelt werden sollen.
	 * @return possibleCells Eine LinkedList, auf dem die Zellen in denen Number stehen koennte, gespeichert sind.
	 */
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
	
	/**
	 * Submethode zu der Methode getPossibleCells.
	 * Liefert die Ankerzellen der Bloecke zurueck, in denen number nicht enthalten ist.
	 * 
	 * @param grid Das Sudoku auf dem die Zellen ermittelt werden
	 * @param number Die Zahl deren Erweiterungen ermittelt werden sollen.
	 * @return temp LinkedList der Bloecke in denen number nicht enthalten ist.
	 */
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

	/**
	 * Submethode zu der Methode getPossibleCells.
	 * Entscheidet, ob die uebergebene Zelle eine moegliche fuer die Belegung mit number ist.
	 * 
	 * @param grid Das Sudoku auf dem die Zellen ermittelt werden
	 * @param a Die Zelle die aktuell geprueft wird.
	 * @param number Die Zahl deren Erweiterungen ermittelt werden sollen.
	 * @return boolean true, falls es eine moegliche Zelle ist. False, falls nicht.
	 */
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
	 * Submethode zu der Methode putNumberColBlock..
	 * Ueberprueft, ob die Voraussetzungen der Methode auf dieses Grid zustimmen.
	 * 
	 * @param grid Das Sudoku auf dem geprueft wird
	 * @param number Die Zahl deren Erweiterungen ermittelt werden sollen.
	 * @return boolean true falls die Voraussetzungen erfuellt sind. False, falls nicht.
	 */
	public boolean rulesConfirmed(Grid grid, int number){
		LinkedList<Cell> blocksWithoutNumber = blocksWithoutNumber(grid, number);
		int nope = 0;
		boolean answer = true;
		
		label1:for(int k = 0; k < blocksWithoutNumber.size(); k++){
			nope = 0;
			LinkedList<Cell> oneBlock = new LinkedList<Cell>();
			
			for(int i = blocksWithoutNumber.get(k).getrIndex(); i < blocksWithoutNumber.get(k).getrIndex() + 3; i++){
				for(int j = blocksWithoutNumber.get(k).getcIndex(); j < blocksWithoutNumber.get(k).getcIndex() + 3; j++){
					
					oneBlock.add(grid.getCell(i, j));
					
				}
			}
			
			for(int i = 0; i < oneBlock.size(); i++){
				if(possibleForNumber(grid, oneBlock.get(i), number) == true){
					nope++;
				}
			}
			
			if(nope == 0){
				answer = false;
				break label1;
			}
		}
		
		label2:for(int i = 1; i < 10; i++){
			int[] numbers = {0,0,0,0,0,0,0,0,0};
			
			for(int j = 0; j < grid.getColValues(i).length; j++){
				if(grid.getColValues(i)[j] != -1){
					numbers[(grid.getColValues(i)[j])-1]++;
				}
			}
			
			for(int j = 0; j < numbers.length; j++){
				if(numbers[j] > 1){
					answer = false;
					break label2;
				}
			}
		}
		return answer;
	}
	
	/**
	 * Hilfsmethode zu hasRowConflictFree.
	 * Ueberprueft auf dem grid, ob es nur aus Nullen besteht - also ein Nullergrid ist.
	 * 
	 * @param temp Sudoku, auf dem die Anzahl der Nullen ermittelt wird.
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
	 * Hilfsmethode zu putNumberColBlock.
	 * Ueberprueft auf dem grid, ob es nur aus -1 besteht - also leer ist.
	 * 
	 * @param temp Sudoku, auf dem die Anzahl der Nullen ermittelt wird.
	 * @return boolean Besteht das Sudoku nur aus Nullen, so wird true zurueckgegeben. Sonst false.
	 */
	public boolean isEmptyGrid(Grid temp) {
		int nullCounter = 0;
		
		int row = 1;
		while(row < 10){
			for(int rIndex = 0; rIndex < temp.getRowValues(row).length; rIndex++){
				if(temp.getRowValues(row)[rIndex] == -1){
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
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
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

	/**
	 * Submethode zu der Methode putNumberColBlock..
	 * Ueberprueft, ob das uebergebene Grid teilbefuellt ist, oder nicht.
	 * 
	 * @param grid Das Sudoku auf dem geprueft wird
	 * @return boolean true falls teilbefuellt. False, falls nicht.
	 */
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
	
}
