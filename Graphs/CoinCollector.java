import java.io.*;
import java.util.*;

public class CoinCollector {

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

    static void dfs(int node, List<List<Integer>> adj, boolean[] visited, Deque<Integer> dq){
        visited[node] = true;

        for(int it : adj.get(node)){
            if(!visited[it]){
                dfs(it, adj, visited, dq);
            }
        }

        dq.push(node);
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
        int[] coins = new int[n + 1];

        for(int i = 1; i <= n; i++){
            coins[i] = in.nextInt();
        }

        for(int i = 0; i <= n; i++){
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
        Deque<Integer> dq = new ArrayDeque<>();

        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                dfs(i, adj, visited, dq);
            }
        }

        visited = new boolean[n + 1];
        int scc = 0;
        int[] comp = new int[n + 1];

        while(!dq.isEmpty()){
            int node = dq.pop();

            if(!visited[node]){
                scc += 1;
                dfsNew(node, rev, visited, scc, comp);
            }
        }

        List<Set<Integer>> dag = new ArrayList<>();
        long[] coinSum = new long[scc + 1];
        int[] indegree = new int[scc + 1];

        for(int i = 0; i <= scc; i++){
            dag.add(new HashSet<>());
        }


        for(int i = 1; i <= n; i++){
            List<Integer> list = adj.get(i);
            coinSum[comp[i]] += coins[i];

            for(int it : list){
                int u = comp[i];
                int v = comp[it];
                if(u != v && !dag.get(u).contains(v)){
                    dag.get(u).add(v);
                    indegree[v] += 1;
                }
            }
        }

        long[] dp = new long[scc + 1];
        Queue<Integer> q = new LinkedList<>();

        for(int i = 1; i <= scc; i++){
            dp[i] = coinSum[i];
        }

        for(int i = 1; i <= scc; i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int node = q.poll();

            for(int it : dag.get(node)){
                dp[it] = Math.max(dp[it], dp[node] + coinSum[it]);
                indegree[it] -= 1;
                if(indegree[it] == 0){
                    q.add(it);
                }

            }
        }

        long max = 0;
        for(long it : dp){
            max = Math.max(max, it);
        }

        out.println(max);

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