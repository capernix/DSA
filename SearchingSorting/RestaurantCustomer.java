import java.io.*;
import java.util.*;

public class RestaurantCustomer {

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
        List<int[]> list = new ArrayList<>();

        for(int i = 0; i < n; i++){
            int arrival = in.nextInt();
            int departure = in.nextInt();

            list.add(new int[]{arrival, 1});
            list.add(new int[]{departure, -1});
        }

        int currentCustomers = 0;
        int res = 0;

        Collections.sort(list, (a, b) -> {
            if(a[0] == b[0]){
                return a[1] - b[1];
            }

            return a[0] - b[0];
        });

        for(int[] time : list){
            currentCustomers += time[1];
            res = Math.max(res, currentCustomers);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(res);

        out.print(res);
        out.close();
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