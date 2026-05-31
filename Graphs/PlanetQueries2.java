import java.io.*;
import java.util.*;

public class PlanetQueries2 {

    static FastReader in = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);

    static final int LOG = 31;
    static int n, q;
    static int[] comp;
    static int[] indegree;
    static boolean[] inCycle;
    static int[] cyclePos;
    static int[] cycleSize;
    static int[] depth;
    static List<List<Integer>> rev;
    static int[][] up;
    

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
        int q = in.nextInt();

        indegree = new int[n + 1];
        rev = new ArrayList<>();

        up = new int[n + 1][LOG];

        for(int i = 0; i <= n; i++){
            rev.add(new ArrayList<>());
        }

        for(int i = 1; i <= n; i++){
            up[i][0] = in.nextInt();

            indegree[up[i][0]] += 1;

            rev.get(up[i][0]).add(i);
        }

        for(int j = 1; j < LOG; j++){

            for(int i = 1; i <= n; i++){
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        inCycle = new boolean[n + 1];

        for(int i = 1; i <= n; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()){
            int node = queue.poll();

            int next = up[node][0];

            indegree[next] -= 1;

            if(indegree[next] == 0){
                queue.offer(next);
            }
        }

        depth = new int[n + 1];

        Arrays.fill(depth, -1);

        comp = new int[n + 1];
        cyclePos = new int[n + 1];
        cycleSize = new int[n + 1];

        int cid = 1;

        for(int i = 1; i <= n; i++){
            if(indegree[i] > 0 && !inCycle[i]){
                int cur = i;

                List<Integer> cycle = new ArrayList<>();

                while(!inCycle[cur]){
                    inCycle[cur] = true;

                    depth[cur] = 0;

                    comp[cur] = cid;

                    cyclePos[cur] = cycle.size();
                    
                    cycle.add(cur);
                    
                    cur = up[cur][0];
                }

                int sz = cycle.size();

                for(int it : cycle){
                    cycleSize[it] = sz;
                }

                cid += 1;
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

            for(int it : rev.get(node)){

                if(depth[it] != -1) continue;

                depth[it] = depth[node] + 1;

                comp[it] = comp[node];

                bfs.offer(it);
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < q; i++){
            int a = in.nextInt();
            int b = in.nextInt();

            if(comp[a] != comp[b]){
                sb.append(-1).append('\n');
                continue;
            }

            if(!inCycle[b]){

                if(depth[a] < depth[b]){
                    sb.append(-1).append('\n');
                    continue;
                }

                int diff = depth[a] - depth[b];

                int lifted = lift(a, diff);

                if(lifted == b){
                    sb.append(diff).append('\n');
                } else{
                    sb.append(-1).append('\n');
                }
            } else{
                int ans = 0;

                if(!inCycle[a]){

                    ans += depth[a];
                    a = lift(a, depth[a]);
                }

                int sz = cycleSize[a];

                int add = (cyclePos[b] - cyclePos[a] + sz) % sz;

                ans += add;
                sb.append(ans).append('\n');
            }
        }

        out.println(sb);

    }

    static int lift(int node, int j){
        int x = node;

        for(int i = 0; i < LOG; i++){
            if((j & (1 << i)) != 0){
                x = up[x][i];
            }
        }

        return x;
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