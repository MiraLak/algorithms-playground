package fr.miralak.algo.perlocation;


public class UnionFind {

    private int[] id;
    private int[] sz;

    // O(N)
    public void initUnionFind(int n) {
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 0;
        }
    }

    // find: O(N)
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    // union: O(N)
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        id[i] = j;
    }

    // find: best O(1), worst O(N)
    private int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }
}
