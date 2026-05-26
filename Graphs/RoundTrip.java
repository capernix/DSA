import java.io.*;
import java.util.*;

public class RoundTrip {

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

    static int cycleStart = -1;
    static int cycleEnd = -1;

    static boolean dfs(int node, int adjNode, List<List<Integer>> adj, boolean[] visited, int[] parent, int n, int m){
        
        visited[node] = true;

        for(int it : adj.get(node)){

            if(it == adjNode) continue;

            if(visited[it]){
                cycleStart = it;
                cycleEnd = node;
                return true;
            }

            parent[it] = node;

            if(dfs(it, node, adj, visited, parent, n, m)){
                return true;
            }

        }
        
        return false;
    }

static void solve() {
        int n = in.nextInt();
        int m = in.nextInt();

        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i <= n; i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            int a = in.nextInt();
            int b = in.nextInt();

            adj.get(a).add(b);
            adj.get(b).add(a);

        }

        boolean[] visited = new boolean[n + 1];

        int[] parent = new int[n + 1];
        int idx = -1;

        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                if(dfs(i, -1, adj, visited, parent, n, m)){
                    break;
                }
            }

            if(i == n){
            out.println("IMPOSSIBLE");
            return;
            }
        }

        List<Integer> path = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        path.add(cycleStart);

        for(int v = cycleEnd; v != cycleStart; v = parent[v]){
            path.add(v);
        }

        path.add(cycleStart);

        sb.reverse();

        for(int x : path){
            sb.append(x).append(" ");
        }

        out.println(path.size());
        out.print(sb);
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