import java.io.*;
import java.util.*;

public class PlanetCycles {

    static FastReader in = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);

    static int[] depth;
    static int[] cycleSize;
    static boolean[] inCycle;
    static int[] indegree;
    static List<List<Integer>> rev;
    static int[] to;

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

        indegree = new int[n + 1];
        rev = new ArrayList<>();
        to = new int[n + 1];

        for(int i = 0; i <= n; i++){
            rev.add(new ArrayList<>());
        }

        for(int i = 1; i <= n; i++){
            int x = in.nextInt();

            to[i] = x;

            indegree[x] += 1;
            rev.get(x).add(i);
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i = 1; i <= n; i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }

        cycleSize = new int[n + 1];
        depth = new int[n + 1];
        Arrays.fill(depth, -1);
        inCycle = new boolean[n + 1];

        while(!q.isEmpty()){
            int node = q.poll();

            int next = to[node];

            indegree[next] -= 1;

            if(indegree[next] == 0){
                q.offer(next);
            }
        }

        for(int i = 1; i <= n; i++){
            if(indegree[i] > 0 && !inCycle[i]){
                int cur = i;
                List<Integer> cycle = new ArrayList<>();

                while(!inCycle[cur]){
                    inCycle[cur] = true;

                    cycle.add(cur);

                    depth[cur] = 0;

                    cur = to[cur];
                }

                int sz = cycle.size();

                for(int it : cycle){
                    cycleSize[it] = sz;
                }
            }
        }

        Queue<Integer> bfs = new LinkedList<>();

        for(int i = 1; i <= n; i++){
            if(inCycle[i]){
                bfs.offer(i);
            }
        }

        while(!bfs.isEmpty()){
            int node = bfs.poll();

            for(int child : rev.get(node)){
                if(depth[child] != -1) continue;

                depth[child] = depth[node] + 1;
                cycleSize[child] = cycleSize[node];

                bfs.offer(child);
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= n; i++){
            sb.append(depth[i] + cycleSize[i]).append(" ");
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