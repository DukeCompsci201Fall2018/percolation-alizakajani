import java.util.Arrays;

/**
 * New implementation of the IPercolate interface that implements
 * the Union-Find data structure. This class serves as a bridge between
 * classes that implement IPercolate and ones that implement IUnionFind
 * 
 * @author Aliza Kajani
 */
public class PercolationUF implements IPercolate {
	
	private boolean[][] myGrid;
	private int myOpenCount;
	private IUnionFind myFinder;
	private final int VTOP;
	private final int VBOTTOM;
	
	/**
	 * 
	 * @param size
	 * @param finder
	 */
	public PercolationUF(int size, IUnionFind finder){
		myGrid = new boolean[size][size];
		for (boolean[] row : myGrid) {
			Arrays.fill(row, false);
		}
		myOpenCount = 0;
		finder.initialize(size*size + 2);
		myFinder = finder;
		VTOP = size*size;
		VBOTTOM = size*size + 1;
	}
	
	public void open(int row, int col) {
		
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		if (myGrid[row][col]) return;
		
		myOpenCount += 1;
		myGrid[row][col] = true;
		
		int q = cellVal(row,col);
		if (row == 0)
			myFinder.union(q, VTOP);
		if (row == myGrid.length-1)
			myFinder.union(q, VBOTTOM);
		
		int[] rowDelta = {-1,1,0,0};
		int[] colDelta = {0,0,-1,1};
		
		for (int k=0; k < rowDelta.length; k++) {
			row = q / myGrid.length + rowDelta[k];
			col = q % myGrid.length + colDelta[k];
			if (inBounds(row,col) && isOpen(row,col)) {
				myFinder.union(q, cellVal(row,col));
				if (row == 0) 
					myFinder.union(cellVal(row,col), VTOP);
				else if (row == myGrid.length-1) 
					myFinder.union(cellVal(row,col), VBOTTOM);
			}
		}
		
	}
	
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col];
	}

	public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myFinder.connected(cellVal(row,col), VTOP);
	}
	
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}
	
	public int numberOfOpenSites() {
		return myOpenCount;
	}
	
	/**
	 * Determine if (row,col) is valid for given grid
	 * @param row specifies row
	 * @param col specifies column
	 * @return true if (row,col) on grid, false otherwise
	 */
	protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
	
	/**
	 * helper method to map (row,col) pairs to an integer value
	 * @param row
	 * @param col
	 * @return
	 */
	protected int cellVal(int row, int col) {
		return row * myGrid.length + col;
	}
	
	
}
