import java.io.*;
import java.util.*;

public class MailDelivery {

    static class Edge{
        int to, id;

        Edge(int to, int id){
            this.to = to;
            this.id = id;
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

        List<List<Edge>> adj = new ArrayList<>();
        int[] degree = new int[n + 1];

        for(int i = 0; i <= n; i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            int a = in.nextInt();
            int b = in.nextInt();

            adj.get(a).add(new Edge(b, i));
            adj.get(b).add(new Edge(a, i));

            degree[a] += 1;
            degree[b] += 1;
        }

        for(int i = 1; i <= n; i++){
            if((degree[i] & 1) == 1){
                out.println("IMPOSSIBLE");
                return;
            }
        }


        List<Integer> path = eulerTour(1, adj, m);

        StringBuilder sb = new StringBuilder();

        if(path.size() != m + 1){
            out.println("IMPOSSIBLE");
            return;
        } else{
            for(int it : path){
                sb.append(it).append(" ");
            }
        }

        out.println(sb);
    }

    static List<Integer> eulerTour(int start, List<List<Edge>> adj, int m) {

    boolean[] used = new boolean[m];

    Stack<Integer> stack = new Stack<>();
    List<Integer> path = new ArrayList<>();

    stack.push(start);

    while (!stack.isEmpty()) {

        int node = stack.peek();

        while (!adj.get(node).isEmpty() &&
               used[adj.get(node).get(adj.get(node).size() - 1).id]) {

            adj.get(node).remove(adj.get(node).size() - 1);
        }

        if (adj.get(node).isEmpty()) {

            path.add(node);
            stack.pop();

        } else {

            Edge e = adj.get(node).remove(adj.get(node).size() - 1);

            used[e.id] = true;

            stack.push(e.to);
        }
    }

    return path;
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