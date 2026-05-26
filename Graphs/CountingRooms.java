import java.io.*;
import java.util.*;

public class CountingRooms {

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

    static void bfs(int row, int col, char[][] grid, boolean[][] visited, int n, int m){

    Queue<int[]> q = new LinkedList<>();

    q.offer(new int[]{row, col});
    visited[row][col] = true;

    int[] delrow = {0, 0, 1, -1};
    int[] delcol = {-1, 1, 0, 0};

    while(!q.isEmpty()){

        int[] cur = q.poll();

        int r = cur[0];
        int c = cur[1];

        for(int i = 0; i < 4; i++){

            int nrow = r + delrow[i];
            int ncol = c + delcol[i];

            if(nrow >= 0 && nrow < n &&
               ncol >= 0 && ncol < m &&
               grid[nrow][ncol] == '.' &&
               !visited[nrow][ncol]){

                visited[nrow][ncol] = true;
                q.offer(new int[]{nrow, ncol});
            }
        }
    }
}

    static void solve() {
        int n = in.nextInt();
        int m = in.nextInt();

        boolean[][] visited = new boolean[n][m];
        char[][] grid = new char[n][m];

        for(int i = 0; i < n; i++){
            grid[i] = in.next().toCharArray();
        }

        int rooms = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == '.' && !visited[i][j]){
                    bfs(i, j, grid, visited, n, m);
                    rooms += 1;
                }
            }
        }

        out.print(rooms);
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