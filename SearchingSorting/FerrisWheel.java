package SearchingSorting;
import java.io.*;
import java.util.*;

public class FerrisWheel {
    static FastReader in = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        // int t = in.nextInt();
        int t = 1;
        for (int test = 0; test < t; test++) {
            solve();
        }
        out.close();
    }

    static void solve() {

        int n = in.nextInt();
        int x = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int cnt = 0;
        int l = 0, r = n - 1;

        Arrays.sort(arr);
        
        while(l < r){
            if(arr[l] + arr[r] > x){
                r -= 1;
            } else{
                l += 1;
                r -= 1;
            }

            cnt += 1;
        }

        if(l == r) cnt += 1;

        out.print(cnt);
    }

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
        a = a % mod;
        while (b > 0) {
            if ((b & 1) == 1) {
                result = (result * a) % mod;
            }
            a = (a * a) % mod;
            b >>= 1;
        }
        return result;
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(s));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null) {
                        return null;
                    }
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
