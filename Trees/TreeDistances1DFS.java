import java.io.*;
import java.util.*;

public class TreeDistances1DFS {

    static class Pair{
        int node, parent, level;

        Pair(int node, int parent, int level){
            this.node = node;
            this.parent = parent;
            this.level = level;
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

        int[] distA = new int[n + 1];
        int[] distB = new int[n + 1];
        int[] ans = new int[n + 1];

        bfs(1, 0, tree);

        int A = farthestNode;

        bfs(A, 0, tree);

        int B = farthestNode;

        bfs2(A, 0, distA, tree);

        bfs2(B, 0, distB, tree);

        for(int i = 1; i <= n; i++){
            ans[i] = Math.max(distA[i], distB[i]);
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= n; i++){
            sb.append(ans[i]).append(" ");
        }

        out.println(sb);
    }

    static int maxDist = 0;
    static int farthestNode = 0;

    static void bfs(int node1, int parent1, List<List<Integer>> adj){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(node1, parent1, 0));

        farthestNode = node1;
        maxDist = 0;

        while(!q.isEmpty()){
            Pair p = q.poll();

            int node = p.node;
            int parent = p.parent;
            int level = p.level;

            if(level > maxDist){
                maxDist = level;
                farthestNode = node;
            }

            for(int child : adj.get(node)){
                if(child == parent) continue;

                q.add(new Pair(child, node, level + 1));
            }
        }
    }

    static void bfs2(int node1, int parent1, int[] dist, List<List<Integer>> adj){

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(node1, parent1, 0));
        dist[node1] = 0;
        
        while(!q.isEmpty()){
            Pair p = q.poll();

            int node = p.node;
            int parent = p.parent;
            int level = p.level;

            dist[node] = level;

            for(int child : adj.get(node)){
                if(child == parent) continue;

                q.add(new Pair(child, node, level + 1));
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