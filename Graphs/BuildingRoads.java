import java.io.*;
import java.util.*;

public class BuildingRoads {

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

        void union(int u, int v){
            int ux = find(u);
            int uv = find (v);

            if(ux == uv) return;

            if(rank[ux] > rank[uv]){
                parent[uv] = ux;
            }
            else if(rank[ux] < rank[uv]){
                parent[ux] = uv;
            } else{
                parent[ux] = uv;
                rank[uv] += 1;
            }
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

        DSU ds = new DSU(n);

        for(int i = 0; i < m; i++){
            int a = in.nextInt();
            int b = in.nextInt();


            ds.union(a, b);
        }

        int cnt = 0;
        List<Integer> list = new ArrayList<>();

        for(int i = 1; i <= n; i++){
            if(ds.find(i) == i){
                cnt += 1;
                list.add(i);
            } 
        }

        int first = list.get(0);
        int size = list.size();

        StringBuilder sb = new StringBuilder();
        sb.append(cnt - 1).append('\n');

        for(int i = 1; i < size; i++){
            sb.append(first).append(" ").append(list.get(i)).append('\n');
        }

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