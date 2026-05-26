import java.io.*;
import java.util.*;

public class Labyrinth {

    static class Pair{
        int first, second;

        Pair(int first, int second){
            this.first = first;
            this.second = second;
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

        boolean[][] visited = new boolean[n][m];
        char[][] grid = new char[n][m];

        int[] delrow = {0, 0, 1, -1};
        int[] delcol = {1, -1, 0, 0};

        char[] dir = {'R', 'L', 'D', 'U'};

        char[][] parent = new char[n][m];

        Pair start = null, end = null;

        for(int i = 0; i < n; i++){
            grid[i] = in.next().toCharArray();
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 'A'){
                    start = new Pair(i, j);
                } else if(grid[i][j] == 'B'){
                    end = new Pair(i, j);
                }
            }
        }

        Queue<Pair> q = new LinkedList<>();
        q.add(start);
        visited[start.first][start.second] = true;

        while(!q.isEmpty()){
            Pair p = q.poll();

            for(int i = 0; i < 4; i++){
                int nrow = p.first + delrow[i];
                int ncol = p.second + delcol[i];

                if(nrow < 0 || ncol < 0 || nrow >= n || ncol >= m)
                    continue;

                if(visited[nrow][ncol] || grid[nrow][ncol] == '#')
                    continue;

                q.add(new Pair(nrow, ncol));
                visited[nrow][ncol] = true;

                parent[nrow][ncol] = dir[i];
            }
        }

        if(!visited[end.first][end.second]){
            out.print("NO");
            return;
        }

        StringBuilder sb = new StringBuilder();

        int ex = end.first;
        int ey = end.second;

        while(ex != start.first || ey != start.second){
            char ch = parent[ex][ey];

            sb.append(ch);

            switch(ch){
                case 'U':
                    ex += 1;
                    break;
                case 'D':
                    ex -= 1;
                    break;
                case 'L':
                    ey += 1;
                    break;
                case 'R':
                    ey -= 1;
                    break;
            }
        }

        sb.reverse();

        out.println("YES");
        out.println(sb.length());
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