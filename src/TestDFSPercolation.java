import static org.junit.Assert.*;

import org.junit.Test;

public class TestDFSPercolation {
	
	public IPercolate getDFSPercolater(int size) {
		// return new PercolationDFS(size);
		// return new PercolationDFSFast(size);
		return new PercolationBFS(size);
	}
	
	/**
	 * test checks if PercolationDFS' isOpen method works correctly
	 */
	@Test(timeout = 20000)
	public void testDFSIsOpen() {
		IPercolate dfs = getDFSPercolater(10); 
		for (int i = 1; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				dfs.open(i, j);
				assertTrue("This test checks if PercolationDFS' isOpen method " + "works correctly", dfs.isOpen(i, j));
			}
	}

	
	/**
	 * This test checks if PercolationDFS' isFull method works correctly
	 */
	@Test(timeout = 20000)
	public void testDFSIsFull() {
		IPercolate dfs = getDFSPercolater(10); 
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				dfs.open(i, j);
				assertTrue("This test checks if PercolationDFS' isFull method " + "works correctly", dfs.isFull(i, j));
			}
	}


	private void testPercolates(IPercolate perc) {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 10; j++) {
				perc.open(i, j);
				assertFalse("This test checks if " + perc.getClass().getName() + " percolates method works correctly",
						perc.percolates());
			}
		perc.open(9, 0);
		assertTrue("This test checks if " + perc.getClass().getName() + "percolates method works correctly",
				perc.percolates());
	}

	/**
	 * This test checks if PercolationDFS' percolates method works correctly
	 */
	@Test(timeout = 20000)
	public void testDFSPercolates() {
		IPercolate dfs = getDFSPercolater(10); 
		testPercolates(dfs);
	}

	

	/**
	 * Check if Exception is thrown unless (0 <= i < N) and (0 <= j < N)
	 */
	private static void bounds(IPercolate perc, int N, int i, int j) {
		boolean passed1 = false;
		boolean passed2 = false;
		boolean passed3 = false;
		System.out.println("  *  N = " + N + ", (i, j) = (" + i + ", " + j + ")");
		try {
			perc.open(i, j);
		} catch (Exception e) {
			passed1 = true;
		}
		assertTrue("This test checks if Exception thrown for open() for " + perc.getClass().getName(), passed1);

		try {
			boolean b = perc.isOpen(i, j);
		} catch (Exception e) {
			passed2 = true;
		}
		assertTrue("This test checks if Exception thrown for isOpen() for " + perc.getClass().getName(), passed2);

		try {
			boolean b = perc.isFull(i, j);
		} catch (Exception e) {
			passed3 = true;
		}
		assertTrue("This test checks if Exception thrown for isFull() for " + perc.getClass().getName(), passed3);
	}

	private void testBounds(IPercolate perc) {
        bounds(perc, 10, -1,  5);
        bounds(perc, 10, 11,  5);
        bounds(perc, 10, 10,  5);
        bounds(perc, 10,  5, -1);
        bounds(perc, 10,  5, 11);
        bounds(perc, 10,  5, 10);
	}
	/**
	 * Check if Exception is thrown when (i, j) are out of bounds
	 */
	@Test(timeout = 2000)
	public void testBounds() {
		IPercolate dfs = getDFSPercolater(10); 
		testBounds(dfs);
	}

}
