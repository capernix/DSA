import java.io.*;
import java.util.*;

public class GiantPizza {

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

    static int node(int x, int m){
        if(x > 0) return x;
        return -x + m;
    }

    static int neg(int x, int m){
        if(x <= m) return x + m;
        return x - m;
    }

    static void dfs(int node, List<List<Integer>> adj, boolean[] visited, Stack<Integer> st){
        visited[node] = true;

        for(int it : adj.get(node)){
            if(!visited[it]){
                dfs(it, adj, visited, st);
            }
        }

        st.push(node);
    }

    static void dfsNew(int node, List<List<Integer>> adj, boolean[] visited, int id, int[] comp){
        visited[node] = true;

        comp[node] = id;

        for(int it : adj.get(node)){
            if(!visited[it]){
                dfsNew(it, adj, visited, id, comp);
            }
        }
    }

    static void solve() {
        int n = in.nextInt();
        int m = in.nextInt();

        List<List<Integer>> adj = new ArrayList<>();
        List<List<Integer>> rev = new ArrayList<>();

        for(int i = 0; i <= 2 * m; i++){
            adj.add(new ArrayList<>());
            rev.add(new ArrayList<>());
        }

        for(int i = 0; i < n; i++){
            String s1 = in.next();
            int a = in.nextInt();

            String s2 = in.next();
            int b = in.nextInt();

            if(s1.equals("-")) a = -a;
            if(s2.equals("-")) b = -b;

            int u = node(a, m);
            int v = node(b, m);

            int nu = neg(u, m);
            int nv = neg(v, m);

            adj.get(nu).add(v);
            adj.get(nv).add(u);

            rev.get(v).add(nu);
            rev.get(u).add(nv);
        }

        boolean[] visited = new boolean[2 * m + 1];
        Stack<Integer> st = new Stack<>();

        for(int i = 1; i <= 2 * m; i++){
            if(!visited[i]){
                dfs(i, adj, visited, st);
            }
        }

        visited = new boolean[2 * m + 1];
        int scc = 0;
        int[] comp = new int[2 * m + 1];

        while(!st.isEmpty()){
            int node = st.pop();

            if(!visited[node]){
                scc += 1;
                dfsNew(node, rev, visited, scc, comp);
            }
        }

        for(int i = 1; i <= m; i++){
            if(comp[i] == comp[i + m]){
                out.println("IMPOSSIBLE");
                return;
            }
        }

        char[] ans = new char[m + 1];

        for(int i = 1; i <= m; i++){
            if(comp[i] > comp[i + m]){
                ans[i] = '+';
            } else{
                ans[i] = '-';
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= m; i++){
            sb.append(ans[i]).append(" ");
        }

        out.println(sb);

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