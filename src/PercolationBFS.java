import java.util.LinkedList;
import java.util.Queue;

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
	 * Mark all cells that are open and reachable from (row,col) as FULL
	 * using a Queue and a BFS rather than recursion
	 * 
	 * @param row is the row coordinate of the cell being checked/marked
	 * @param col is the col coordinate of the cell being checked/marked
	 */
	protected void dfs(int row, int col) {
		// out of bounds?
		if (! inBounds(row,col)) return;
		
		// full or NOT open, don't process
		if (isFull(row, col) || !isOpen(row, col)) return;
		
		int[] rowDelta = {-1,1,0,0};
		int[] colDelta = {0,0,-1,1};
		myGrid[row][col] = FULL;
		Queue<Integer> bfsQ = new LinkedList<>();
		bfsQ.add(cellVal(row,col));
		
		while (bfsQ.size() != 0) {
			int q = bfsQ.remove();
			for(int k=0; k < rowDelta.length; k++) {
				row = q / myGrid.length + rowDelta[k];
				col = q % myGrid.length + colDelta[k];
				if (inBounds(row,col) && !isFull(row,col) && isOpen(row,col)) {
					myGrid[row][col] = FULL;
					bfsQ.add(cellVal(row,col));
				}
			}
		}
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
