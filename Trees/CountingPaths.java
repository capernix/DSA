import java.io.*;
import java.util.*;

public class CountingPaths {

    static final int LOG = 20;

    static ArrayList<Integer>[] tree;
    static int[][] up;
    static int[] depth;
    static int[] parent;
    static int[] order;
    static long[] cnt;

    public static void main(String[] args) throws Exception {

        FastReader in = new FastReader();

        int n = in.nextInt();
        int m = in.nextInt();

        tree = new ArrayList[n + 1];

        for(int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; i++) {
            int a = in.nextInt();
            int b = in.nextInt();

            tree[a].add(b);
            tree[b].add(a);
        }

        parent = new int[n + 1];
        depth = new int[n + 1];
        order = new int[n];

        buildTree(n);

        up = new int[n + 1][LOG];

        for(int i = 1; i <= n; i++) {
            up[i][0] = parent[i];
        }

        for(int j = 1; j < LOG; j++) {
            for(int i = 1; i <= n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }

        cnt = new long[n + 1];

        for(int i = 0; i < m; i++) {

            int u = in.nextInt();
            int v = in.nextInt();

            int l = lca(u, v);

            cnt[u]++;
            cnt[v]++;

            cnt[l]--;

            if(parent[l] != 0) {
                cnt[parent[l]]--;
            }
        }

        for(int i = n - 1; i > 0; i--) {

            int node = order[i];

            cnt[parent[node]] += cnt[node];
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= n; i++) {
            sb.append(cnt[i]).append(' ');
        }

        System.out.println(sb);
    }

    static void buildTree(int n) {

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        stack.push(1);

        parent[1] = 0;
        depth[1] = 0;

        int idx = 0;

        while(!stack.isEmpty()) {

            int node = stack.pop();

            order[idx++] = node;

            for(int child : tree[node]) {

                if(child == parent[node]) continue;

                parent[child] = node;
                depth[child] = depth[node] + 1;

                stack.push(child);
            }
        }
    }

    static int lca(int u, int v) {

        if(depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for(int j = 0; j < LOG; j++) {
            if((diff & (1 << j)) != 0) {
                u = up[u][j];
            }
        }

        if(u == v) return u;

        for(int j = LOG - 1; j >= 0; j--) {

            if(up[u][j] != up[v][j]) {

                u = up[u][j];
                v = up[v][j];
            }
        }

        return up[u][0];
    }

    static class FastReader {

        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];

        private int ptr = 0;
        private int len = 0;

        private int read() {

            if(ptr >= len) {

                ptr = 0;

                try {
                    len = in.read(buffer);
                } catch(IOException e) {
                    return -1;
                }

                if(len <= 0) return -1;
            }

            return buffer[ptr++];
        }

        int nextInt() {

            int c;

            while((c = read()) <= ' ') {
                if(c == -1) return -1;
            }

            int sign = 1;

            if(c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;

            while(c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }

            return val * sign;
        }
    }
}