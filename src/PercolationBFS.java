
/**
 * New implementation of the IPercolate interface that extends the
 * PercolationDFSFast class and has a different implementation of 
 * the method dfs, which uses a Queue and a breadth-first-search,
 * rather than recursion.
 * 
 * @author alizakajani
 */
public class PercolationBFS extends PercolationDFSFast {
	
	/**
	 * Calls super to initialize the state in the parent class,
	 * which is a grid whose cells are all blocked.
	 * 
	 * @param n is the size of the simulated (square, nxn) grid
	 */
	public PercolationBFS(int n) {
		super(n);
	}
	
	
	@Override
	/**
	 * Private helper method to mark all cells that are open and reachable from
	 * (row,col).
	 * 
	 * @param row
	 *            is the row coordinate of the cell being checked/marked
	 * @param col
	 *            is the col coordinate of the cell being checked/marked
	 */
	protected void dfs(int row, int col) {
		// out of bounds?
		if (! inBounds(row,col)) return;
		
		// full or NOT open, don't process
		if (isFull(row, col) || !isOpen(row, col))
			return;
		
		myGrid[row][col] = FULL;
		dfs(row - 1, col);
		dfs(row, col - 1);
		dfs(row, col + 1);
		dfs(row + 1, col);
	}
}
