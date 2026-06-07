import java.io.*;
import java.util.*;

public class Subordinates {

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

    static class Pair{
        int node, state;

        Pair(int node, int state){
            this.node = node;
            this.state = state;
        }
    }

    static void solve() {
        int n = in.nextInt();

        List<List<Integer>> adj = new ArrayList<>();


        for(int i = 0; i <= n; i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 2; i <= n; i++){
            int boss = in.nextInt();

            adj.get(boss).add(i);
        }

        int[] subSize = new int[n + 1];
        dfs(1, adj, subSize);

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= n; i++){
            sb.append(subSize[i] - 1).append(" ");
        }

        out.println(sb);
    }

    static void dfs(int init, List<List<Integer>> adj, int[] subSize){

        Stack<Pair> st = new Stack<>();
        st.push(new Pair(init, 0));

        while(!st.isEmpty()){
            Pair p = st.pop();

            int node = p.node;
            int state = p.state;

            if(state == 0){
                st.push(new Pair(node, 1));

                for(int child: adj.get(node)){
                    st.push(new Pair(child, 0));
                }
            } else{
                int size = 1;

                for(int child : adj.get(node)){
                    size += subSize[child];
                }

                subSize[node] = size;
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