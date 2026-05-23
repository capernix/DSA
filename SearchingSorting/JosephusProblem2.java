import java.io.*;
import java.util.*;

public class JosephusProblem2 {

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

    static class Fenwick {
        int[] bit;
        int n;

        Fenwick(int n){
            this.n = n;
            bit = new int[n + 1];
        }

        void add(int idx, int val){
            while(idx <= n){
                bit[idx] += val;
                idx += idx & -idx;
            }
        }

        int sum(int idx){
            int res = 0;

            while(idx > 0){
                res += bit[idx];
                idx -= idx & -idx;
            }

            return res;
        }

        int kth(int k){
            int idx = 0;
            int bitMask = Integer.highestOneBit(n);

            for(int step = bitMask; step != 0; step >>= 1){
                int next = idx + step;

                if(next <= n && bit[next] < k){
                    idx = next;
                    k -= bit[next];
                }
            }
            return idx + 1;
        }
    }

    static void solve() {
        int n = in.nextInt();
        int k = in.nextInt();

        Fenwick fw = new Fenwick(n);

        for(int i = 1; i <= n; i++){
            fw.add(i, 1);
        }

        int pos = 0;
        StringBuilder sb = new StringBuilder();

        for(int sz = n; sz >= 1; sz--){
            pos = (pos + k) % sz;

            int removed = fw.kth(pos + 1);

            sb.append(removed).append(" ");

            fw.add(removed, -1);
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