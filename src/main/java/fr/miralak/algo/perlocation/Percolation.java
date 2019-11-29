package fr.miralak.algo.perlocation;

/**
 *  Percolation:
 *  Given a composite systems comprised of randomly distributed insulating and metallic materials:
 *  what fraction of the materials need to be metallic so that the composite system is an electrical conductor?
 *  Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able
 *  to drain through to the bottom (or the oil to gush through to the surface)?
 *  Scientists have defined an abstract process known as percolation to model such situations.
 */
public class Percolation {

    int topVirtualRoot = 0;
    private int gridSize = 0;
    int topBottomRoot = 0;
    // We represent the grid in a array
    // The grid size is n
    // One grid site is defined with X and Y values: X is horizontal position and Y vertical position
    // A site position in the array is "(row-n) + (col*n)-1" as site starts from 0 in the array
    private int[] gridArray;
    private UnionFind unionFindAlgorithm;

    // creates n-by-n grid, with all sites initially blocked: 0 for blocked and 1 for open
    public Percolation(int n) {
        gridSize = n;
        unionFindAlgorithm.initUnionFind(n * n + 2); // we add two element for virtual top root and virtual bottom root
        // we connect all top node to virtual top root
        topVirtualRoot = n * n + 1;
        topBottomRoot = n * n + 2;
        for (int i = 0; i < n; i++) {
            unionFindAlgorithm.union(topVirtualRoot, i);
            unionFindAlgorithm.union(topBottomRoot, getIndex(n, i));
        }
    }

    // test client (optional)
    public static void main(String[] args) {

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int index = getIndex(row, col);
        gridArray[index] = 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int index = getIndex(row, col);
        return gridArray[index] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    private int getIndex(int row, int col) {
        return (row - gridSize) + (col * gridSize) - 1;
    }
}
