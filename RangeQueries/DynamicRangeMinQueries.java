import java.io.*;
import java.util.*;

public class DynamicRangeMinQueries {

    static class SegmentTree{
        int n;
        long[] seg;

        SegmentTree(int[] arr){
            this.n = arr.length;

            seg = new long[2 * n];

            for(int i = 0; i < n; i++){
                seg[i + n] = arr[i];
            }

            for(int i = n - 1; i > 0; i--){
                seg[i] = Math.min(seg[i << 1], seg[i << 1 | 1]);
            }
        }

        void update(int pos, int val){
            pos += n;

            seg[pos] = val;

            while(pos > 1){
                pos >>= 1;

                seg[pos] = Math.min(seg[pos << 1], seg[pos << 1 | 1]);
            }
        }

        long query(int a, int b){
            int l = a + n;
            int r = b + n;

            long ans = Long.MAX_VALUE;

            while(l <= r){

                if((l & 1) == 1){
                    ans = Math.min(ans, seg[l++]);
                }

                if((r & 1) == 0){
                    ans = Math.min(ans, seg[r--]);
                }

                l >>= 1;
                r >>= 1;
            }

            return ans;
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
        int q = in.nextInt();

        int[] arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        SegmentTree seg = new SegmentTree(arr);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < q; i++){
            int type = in.nextInt();
            if(type == 1){
                int k = in.nextInt();
                int u = in.nextInt();

                seg.update(k - 1, u);
            } else{
                int a = in.nextInt();
                int b = in.nextInt();

                long ans = seg.query(a - 1, b - 1);
                sb.append(ans).append('\n');
            }
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