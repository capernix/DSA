import java.io.*;
import java.util.*;

public class RoadSeparation {

    static class DSU{
        int n;
        int[] parent, rank;

        DSU(int n){
            this.n = n;
            parent = new int[n + 1];
            rank = new int[n + 1];

            for(int i = 1; i <= n; i++){
                parent[i] = i;
            }
        }

        int find(int x){
            if(parent[x] == x) return x;
            return parent[x] = find(parent[x]);
        }

        boolean union(int x, int y){
            int ux = find(x);
            int uy = find(y);

            if(ux == uy) return false;

            if(rank[ux] > rank[uy]){
                parent[uy] = ux;
            } else{
                if(rank[ux] == rank[uy]){
                    rank[uy] += 1;
                }
                parent[ux] = uy;
            }

            return true;
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
        int m = in.nextInt();

        List<int[]> edges = new ArrayList<>();

        for(int i = 0; i < m; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();

            edges.add(new int[]{a, b, c});
        }

        if(edges.size() < n - 1){
            out.println("IMPOSSIBLE");
            return;
        }

        DSU ds = new DSU(n);
        long sum = 0;
        int edgesUsed = 0;

        Collections.sort(edges, (a, b) -> a[2] - b[2]);

        for(int[] a : edges){
            if(ds.union(a[0], a[1])){
                sum += a[2];
                edgesUsed += 1;
            }
        }

        if(edgesUsed != n - 1){
            out.println("IMPOSSIBLE");
            return;
        }

        out.println(sum);
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