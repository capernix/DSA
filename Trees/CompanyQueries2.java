import java.io.*;
import java.util.*;

public class CompanyQueries2 {

    static class Pair{
        int node, parent, depth;

        Pair(int node, int parent, int depth){
            this.node = node;
            this.parent = parent;
            this.depth = depth;
        }
    }

    static FastReader in = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {

        int t = 1;
        // int t = in.nextInt();

        while (t-- > 0) {
            solve();
        }

        out.close();
    }

    static final int LOG = 21;

    static void solve() {
        int n = in.nextInt();
        int q = in.nextInt();

        List<List<Integer>> tree = new ArrayList<>();
        int[][] up = new int[n + 1][LOG];

        for(int i = 0; i <= n; i++){
            tree.add(new ArrayList<>());
        }

        for(int i = 2; i <= n; i++){
            int boss = in.nextInt();

            tree.get(boss).add(i);

            up[i][0] = boss;
        }

        int[] depth = new int[n + 1];

        dfs(1, 0, tree, depth);

        for(int j = 1; j < LOG; j++){
            for(int i = 1; i <= n; i++){
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < q; i++){
            int a = in.nextInt();
            int b = in.nextInt();

            sb.append(lca(a, b, depth, up)).append('\n');
        }

        out.println(sb);
    }

    static void dfs(int node1, int parent1, List<List<Integer>> tree, int[] depth){

        Stack<Pair> st = new Stack<>();
        st.push(new Pair(node1, parent1, 0));

        while(!st.isEmpty()){
            Pair p = st.pop();

            int node = p.node;
            int parent = p.parent;
            int d = p.depth;

            depth[node] = d;

                for(int child : tree.get(node)){
                    if(child == parent) continue;

                    st.push(new Pair(child, node, d + 1));
                }
        }
    }

    static int lca(int u, int v, int[] depth, int[][] up){

        if(depth[u] < depth[v]){
            int temp = u;
            u = v;
            v = temp;
        }

        u = kthAncestor(u, depth[u] - depth[v], up);

        if(u == v) return u;

        for(int i = LOG - 1; i >= 0; i--){
            if(up[u][i] != up[v][i]){
                u = up[u][i];
                v = up[v][i];
            }
        }

        return up[u][0];
    }

    static int kthAncestor(int node, int k, int[][] up){

        int x = node;

        for(int i = 0; i < LOG; i++){
            if((k & (1 << i)) != 0){
                x = up[x][i];
            }

            if(x == 0) break;
        }

        return x;
    }

    // ================= FAST INPUT =================

    static class FastReader {

        private final InputStream in = System.in;

        private final byte[] buffer = new byte[1 << 16];

        private int ptr = 0;
        private int len = 0;

        private int read() {

            if (ptr >= len) {

                ptr = 0;

                try {
                    len = in.read(buffer);
                } catch (IOException e) {
                    return -1;
                }

                if (len <= 0) return -1;
            }

            return buffer[ptr++];
        }

        int nextInt() {

            int c;

            while ((c = read()) <= ' ') {
                if (c == -1) return -1;
            }

            int sign = 1;

            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }

            return val * sign;
        }

        long nextLong() {

            int c;

            while ((c = read()) <= ' ') {
                if (c == -1) return -1;
            }

            int sign = 1;

            if (c == '-') {
                sign = -1;
                c = read();
            }

            long val = 0;

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }

            return val * sign;
        }

        String next() {

            int c;

            while ((c = read()) <= ' ') {
                if (c == -1) return null;
            }

            StringBuilder sb = new StringBuilder();

            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
        }
    }

    // ================= MATH =================

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    static long modExp(long a, long b, long mod) {

        long result = 1;

        a %= mod;

        while (b > 0) {

            if ((b & 1) == 1) {
                result = (result * a) % mod;
            }

            a = (a * a) % mod;

            b >>= 1;
        }

        return result;
    }
}