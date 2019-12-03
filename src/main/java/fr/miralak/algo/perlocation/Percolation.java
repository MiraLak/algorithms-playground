package fr.miralak.algo.perlocation;

import java.util.Arrays;
import java.util.Scanner;

/**
 *  Percolation:
 *  Given a composite systems comprised of randomly distributed insulating and metallic materials:
 *  what fraction of the materials need to be metallic so that the composite system is an electrical conductor?
 *  Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able
 *  to drain through to the bottom (or the oil to gush through to the surface)?
 *  Scientists have defined an abstract process known as percolation to model such situations.
 */
public class Percolation {

    private int topVirtualRoot = 0;
    private int bottomVirtualRoot = 0;
    private int gridSize = 0;
    private int openSitesCounter = 0;
    // We represent the grid in a array
    // The grid size is n
    // One grid site is defined with X and Y values: X is horizontal position and Y vertical position
    // A site position in the array is "(row-n) + (col*n)-1" as site starts from 0 in the array
    // status could be opened (1) or closed (0)
    private int[] gridArray;
    private UnionFind unionFindAlgorithm = new UnionFind();

    // creates n-by-n grid, with all sites initially blocked: 0 for blocked and 1 for open
    public Percolation(int n) {
        gridSize = n;
        gridArray = new int[n * n + 2];
        unionFindAlgorithm.initUnionFind(n * n + 2); // we add two element for virtual top root and virtual bottom root

        // we connect all top node to virtual top root
        topVirtualRoot = n * n;
        bottomVirtualRoot = n * n + 1;

        // init status with open top and bottom virtual roots
        for (int i = 0; i < n * n + 1; i++) {
            gridArray[i] = 0;
        }
        gridArray[topVirtualRoot] = 1;
        gridArray[bottomVirtualRoot] = 1;
        for (int i = 1; i <= n; i++) {
            unionFindAlgorithm.union(topVirtualRoot, getIndex(1, i));
            unionFindAlgorithm.union(bottomVirtualRoot, getIndex(n, i));
        }
    }

    // test client (optional)
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean perlocationDone = false;

        System.out.println("Welcome to Perlocation algo");
        System.out.println("Enter the grid size: ");
        int gridSize = Integer.parseInt(scanner.nextLine());
        Percolation percolation = new Percolation(gridSize);
        System.out.println("The grid with size " + gridSize + " is initialized");
        System.out.println("Enter the coordinates to open a site in this order: row col");

        while (!perlocationDone) {
            String[] tokens = scanner.nextLine().split("\\s");
            int row = Integer.parseInt(tokens[0]);
            int col = Integer.parseInt(tokens[1]);
            if (percolation.isOpen(row, col)) {
                System.out.println("Site " + Arrays.toString(tokens) + " is already opened");
            } else {
                percolation.open(row, col);
                System.out.println("Site " + Arrays.toString(tokens) + " is open");
            }

            if (percolation.percolates()) {
                perlocationDone = true;
                System.out.println("The grid perlocate! Hourra!!!");
            } else {
                System.out.println("Enter the coordinates to open a site in this order: row col");
            }

        }

        scanner.close();

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int index = getIndex(row, col);
        return gridArray[index] == 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            // set status to open (1)
            int index = getIndex(row, col);
            gridArray[index] = 1;
            openSitesCounter++;

            // update connection to neighbours
            // left neighbour
            if (index - 1 > 0 && isOpen(row, col - 1)) {
                unionFindAlgorithm.union(index, index - 1);
            }
            // right neighbour
            if (index + 1 < gridArray.length - 2 && isOpen(row, col - 1)) {
                unionFindAlgorithm.union(index, index + 1);
            }
            // top neighbour
            if (index - gridSize > 0 && isOpen(row - 1, col)) {
                unionFindAlgorithm.union(index, index - gridSize);
            }
            // bottom neighbour
            if (index + gridSize < gridArray.length - 2 && isOpen(row + 1, col)) {
                unionFindAlgorithm.union(index, index + gridSize);
            }
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return unionFindAlgorithm.connected(getIndex(row, col), topVirtualRoot);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSitesCounter;
    }

    // does the system percolate?
    public boolean percolates() {
        return unionFindAlgorithm.connected(topVirtualRoot, bottomVirtualRoot);
    }

    private int getIndex(int row, int col) {
        return (col - gridSize) + (row * gridSize) - 1;
    }
}
