import java.io.*;
import java.util.*;

public class HighScore {

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

    static class Tuple{
        int u, v;
        long w;

        Tuple(int u, int v, long w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static void dfs(int node, List<List<Integer>> edges, boolean[] reachable){
        reachable[node] = true;

        for(int it : edges.get(node)){
            if(!reachable[it]){
                dfs(it, edges, reachable);
            }
        }
    }

    static void solve() {
        int n = in.nextInt();
        int m = in.nextInt();

        final long INF = (long)1e18;

        List<Tuple> edges = new ArrayList<>();

        List<List<Integer>> rev = new ArrayList<>();
        boolean[] reachable = new boolean[n + 1];

        for(int i = 0; i <= n; i++){
            rev.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();

            edges.add(new Tuple(a, b, -c));

            rev.get(b).add(a);
        }

        long[] dist = new long[n + 1];
        Arrays.fill(dist, INF);

        dfs(n, rev, reachable);

        dist[1] = 0;

        for(int i = 1; i < n; i++){
            for(Tuple t : edges){
                if(dist[t.u] == INF) continue;

                if(dist[t.u] + t.w < dist[t.v]){
                    dist[t.v] = dist[t.u] + t.w;
                }
            }
        }

        for(Tuple t : edges){
            if(dist[t.u] == INF) continue;

            if(dist[t.u] + t.w < dist[t.v]){
                if(reachable[t.v]){
                out.println(-1);
                return;
                }
            }
        }

        out.println(-dist[n]);
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