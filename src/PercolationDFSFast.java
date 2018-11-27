
/**
 * New implementation of the IPercolate interface that uses a faster 
 * depth-first-search than the class PercolationDFS (which it extends)
 * to simulate percolation thresholds for a grid-base system.
 * 
 * @author alizakajani
 */

public class PercolationDFSFast extends PercolationDFS {

	/**
	 * Calls super to initialize the state in the parent class,
	 * which is a grid whose cells are all blocked.
	 * 
	 * @param n is the size of the simulated (square, nxn) grid
	 */
	public PercolationDFSFast(int n) {
		super(n);
	}
	
	/**
	 * More efficient version of updateOnOpen method from parent class.
	 * Instead of clearing all cells each time a new cell is opened, call
	 * dfs once if the newly open cell should be marked as FULL b/c it's
	 * in the top row or b/c it's adjacent to an already FULL cell.
	 * 
	 * @param row is the row coordinate of the cell being checked/marked
	 * @param col is the column coordinate of the cell being checked/marked
	 */
	@Override
	protected void updateOnOpen(int row, int col) {
		// is cell in the top row?
		if (row == 0) dfs(row, col);
		
		// is cell adjacent to a full cell?
		else if ((inBounds(row-1,col) && isFull(row-1,col)) || (inBounds(row,col-1) && isFull(row,col-1)) 
				|| (inBounds(row,col+1)  && isFull(row,col+1)) || (inBounds(row+1,col) && isFull(row+1,col)))
			dfs(row,col);	
	}

}
