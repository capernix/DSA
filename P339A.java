import java.io.*;
import java.util.*;

public class P339A {
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
        String sum_line = in.nextLine();
        int one = 0, two = 0, three = 0;
        for(int i = 0; i < sum_line.length(); i++){
            char ch = sum_line.charAt(i);
            switch(ch){
                case '1':
                    one++;
                    break;
                case '2':
                    two++;
                    break;
                case '3':
                    three++;
                    break;
                default:
                    break;
            }
        }

        for(int i = 0; i < sum_line.length(); i++){
            while(one > 0){
                out.print("1");
                one--;
                if(one > 0 || two > 0 || three > 0){
                    out.print("+");
                }
            }
            while(two > 0){
                out.print("2");
                two--;
                if(two > 0 || three > 0){
                    out.print("+");
                }
            }
            while(three > 0){
                out.print("3");
                three--;
                if(three > 0){
                    out.print("+");
                }
            }
        }
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
