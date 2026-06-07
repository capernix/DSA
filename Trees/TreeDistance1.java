import java.io.*;
import java.util.*;

public class TreeDistance1 {

    static class Pair{
        int node, parent, state;

        Pair(int node, int parent, int state){
            this.node = node;
            this.parent = parent;
            this.state = state;
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

        int[] down = new int[n + 1];
        int[] up = new int[n + 1];
        int[] ans = new int[n + 1];

        dfs(1, 0, down, tree);

        dfs2(1, 0, down, up, ans, tree);

        for(int i = 1; i <= n; i++){
            ans[i] = Math.max(down[i], up[i]);
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= n; i++){
            sb.append(ans[i]).append(" ");
        }

        out.println(sb);
    }

    static void dfs(int node1, int parent1, int[] down, List<List<Integer>> tree){

        Stack<Pair> st = new Stack<>();
        st.push(new Pair(node1, parent1, 0));

        while(!st.isEmpty()){
            Pair p = st.pop();

            int node = p.node;
            int parent = p.parent;
            int state = p.state;

            if(state == 0){
                st.push(new Pair(node, parent, 1));

                for(int child : tree.get(node)){
                    if(child == parent) continue;

                    st.push(new Pair(child, node, 0));
                }
            } else{

                for(int child : tree.get(node)){
                    if(child == parent) continue;
                    
                    down[node] = Math.max(down[node], down[child] + 1);
                }
            }
        }
    }

    static void dfs2(int node1, int parent1, int[] down, int[] up, int[] ans, List<List<Integer>> tree){

        Stack<Pair> st = new Stack<>();
        st.push(new Pair(node1, parent1, 0));

        while(!st.isEmpty()){
            Pair p = st.pop();

            int node = p.node;
            int parent = p.parent;

                int mx1 = 0;
                int mx2 = 0;
                for(int child : tree.get(node)){
                    if(child == parent) continue;

                    int val = down[child] + 1;

                    if(val > mx1){
                        mx2 = mx1;
                        mx1 = val;
                    } else if(val > mx2){
                        mx2 = val;
                    }
                }

                for(int child : tree.get(node)){
                    if(child == parent) continue;

                    int use = mx1;

                    if(down[child] + 1 == mx1){
                        use = mx2;
                    }

                    up[child] = up[node] + 1;

                    if(use != -1){
                        up[child] = Math.max(up[child], use + 1);
                    }

                    st.push(new Pair(child, node, 0));
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