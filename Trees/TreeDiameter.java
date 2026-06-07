import java.io.*;
import java.util.*;

public class TreeDiameter {

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

    static int diameter = 0;

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

        int[] height = new int[n + 1];

        dfs(1, 0, tree, height);

        out.println(diameter);
    }

    static void dfs(int node1, int parent1, List<List<Integer>> tree, int[] height){
        
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
            } else {
                int max1 = 0;
                int max2 = 0;


                for(int child : tree.get(node)){
                    if(child == parent) continue;

                    if(height[child] > max1){
                        max2 = max1;
                        max1 = height[child];
                    } else if(height[child] > max2){
                        max2 = height[child];
                    }
                }

                diameter = Math.max(diameter, max1 + max2);

                height[node] = max1 + 1;
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