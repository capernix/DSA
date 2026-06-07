import java.io.*;
import java.util.*;

public class TreeDistance2 {

    static class Pair{
        int node, parent, depth, state;

        Pair(int node, int parent, int depth, int state){
            this.node = node;
            this.parent = parent;
            this.depth = depth;
            this.state = state;
        }

        Pair(int node, int parent){
            this.node = node;
            this.parent = parent;
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

    static void solve() {
        int n = in.nextInt();

        List<List<Integer>> tree = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            tree.add(new ArrayList<>());
        }

        for(int i = 1; i < n; i++){
            int a = in.nextInt();
            int b = in.nextInt();

            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        int[] subSize = new int[n + 1];
        long[] ans = new long[n + 1];

        dfs(1, 0, subSize, ans, tree);

        dfs2(1, 0, subSize, ans, tree, n);

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= n; i++){
            sb.append(ans[i]).append(" ");
        }

        out.println(sb);
    }

    static void dfs(int node1, int parent1, int[] subSize, long[] ans, List<List<Integer>> tree){

        Stack<Pair> st = new Stack<>();
        st.push(new Pair(node1, parent1, 0, 0));

        long total = 0;

        while(!st.isEmpty()){
            Pair p = st.pop();

            int node = p.node;
            int parent = p.parent;
            int depth = p.depth;
            int state = p.state;

            

            if(state == 0){
                st.push(new Pair(node, parent, depth, 1));

                total += depth;

                for(int child : tree.get(node)){
                    if(child == parent) continue;

                    st.push(new Pair(child, node, depth + 1, 0));
                }
            } else{
                int size = 0;

                for(int child : tree.get(node)){

                    if(child == parent) continue;
                    size += subSize[child];
                }

                subSize[node] = size + 1;
            }
        }

        ans[node1] = total;
    }

    static void dfs2(int node1, int parent1, int[] subSize, long[] ans, List<List<Integer>> tree, int n){

        Stack<Pair> st = new Stack<>();
        st.push(new Pair(node1, parent1));

        while(!st.isEmpty()){
            Pair p = st.pop();

            int node = p.node;
            int parent = p.parent;

            for(int child : tree.get(node)){

            if(child == parent) continue;

            ans[child] = ans[node] + n - 2L * subSize[child];

            st.push(new Pair(child, node));
            }

        }

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