import java.io.*;
import java.util.*;

public class FlightRoutesCheck {

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

    static void dfs(int node, boolean[] visited, List<List<Integer>> adj){
        visited[node] = true;

        for(int it : adj.get(node)){
            if(!visited[it]){
                dfs(it, visited, adj);
            }
        }
    }

    static void solve() {
        int n = in.nextInt();
        int m = in.nextInt();

        List<List<Integer>> adj = new ArrayList<>();
        List<List<Integer>> rev = new ArrayList<>();

        for(int i = 0; i<= n; i++){
            adj.add(new ArrayList<>());
            rev.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            
            adj.get(a).add(b);
            rev.get(b).add(a);
        }

        boolean[] visited = new boolean[n + 1];

        dfs(1, visited, adj);

        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                out.println("NO");
                out.print(1);
                out.print(" ");
                out.print(i);
                return;
            }
        }

        visited = new boolean[n + 1];

        dfs(1, visited, rev);

        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                out.println("NO");
                out.print(i);
                out.print(" ");
                out.print(1);
                return;
            }
        }

        out.println("YES");
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