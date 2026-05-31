import java.io.*;
import java.util.*;

public class Investigation {

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

        final int MOD = 1_000_000_007;

        List<List<int[]>> adj = new ArrayList<>();

        for(int i = 0; i <= n; i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();

            adj.get(a).add(new int[]{b, c});
        }

        long[] dist = new long[n + 1];
        long[] ways = new long[n + 1];
        int[] minF = new int[n + 1];
        int[] maxF = new int[n + 1];

        Arrays.fill(dist, (long)1e18);
        Arrays.fill(minF, (int)1e9);
        Arrays.fill(maxF, -(int)1e9);
        dist[1] = 0;
        ways[1] = 1;
        minF[1] = 0;
        maxF[1] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.add(new long[]{1, 0});

        while(!pq.isEmpty()){
            long[] arr = pq.poll();

            int node = (int)arr[0];
            long dista = arr[1];

            if(dista > dist[node]) continue;

            for(int[] it : adj.get(node)){
                int adjNode = it[0];
                int adjDist = it[1];

                if(dista + adjDist < dist[adjNode]){
                    dist[adjNode] = dista + adjDist;
                    ways[adjNode] = ways[node];
                    minF[adjNode] = minF[node] + 1;
                    maxF[adjNode] = maxF[node] + 1;
                    pq.add(new long[]{adjNode, dist[adjNode]});
                } else if(dista + adjDist == dist[adjNode]){
                    ways[adjNode] = (ways[node] + ways[adjNode]) % MOD;
                    minF[adjNode] = Math.min(minF[adjNode], minF[node] + 1);
                    maxF[adjNode] = Math.max(maxF[adjNode], maxF[node] + 1);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append(dist[n]).append(" ").append(ways[n]).append(" ").append(minF[n]).append(" ").append(maxF[n]);
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