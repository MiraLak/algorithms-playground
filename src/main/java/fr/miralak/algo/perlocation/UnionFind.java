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

    // find: O(log N)
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    // union: O(log N) + finding roots cost
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }

    // find: best O(1), worst O(N)
    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
}
