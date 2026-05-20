package SearchingSorting;
import java.io.*;
import java.util.*;

public class Main {
    // Fast I/O objects
    static FastReader in = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        // If your contest uses multiple test cases, uncomment the next line.
        // int t = in.nextInt();
        // For single test case problems, t remains 1.
        int t = 1;

        // Process each test case.
        for (int test = 0; test < t; test++) {
            solve();
        }
        out.close();
    }

    /**
     * This is where you implement your problem solution.
     * Write your solution code inside this method.
     */
    static void solve() {
        // Example: reading two integers and printing their sum.
        int a = in.nextInt();
        int b = in.nextInt();
        out.println("Sum: " + (a + b));

        // Uncomment and modify the code below for further problem logic.
        /*
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        // Process array 'arr' as needed.
        */
    }

    /**
     * Computes the Greatest Common Divisor (GCD) using Euclidean algorithm.
     * Works for both positive integers.
     */
    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * Overloaded method for computing GCD for long integers.
     */
    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * Computes the Least Common Multiple (LCM) of two integers.
     */
    static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    /**
     * Overloaded method for computing LCM for long integers.
     */
    static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    /**
     * Performs modular exponentiation.
     * Computes (a^b) % mod in O(log b) time.
     */
    static long modExp(long a, long b, long mod) {
        long result = 1;
        a = a % mod;
        while (b > 0) {
            // If b is odd, multiply result by a.
            if ((b & 1) == 1) {
                result = (result * a) % mod;
            }
            // Square a and halve b.
            a = (a * a) % mod;
            b >>= 1;
        }
        return result;
    }

    /**
     * Custom fast input reader using BufferedReader and StringTokenizer.
     */
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        // Default constructor for reading from standard input.
        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        // Constructor for reading from a file (if needed).
        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(s));
        }

        // Reads next token.
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

        // Reads next integer.
        int nextInt() {
            return Integer.parseInt(next());
        }

        // Reads next long.
        long nextLong() {
            return Long.parseLong(next());
        }

        // Reads next double.
        double nextDouble() {
            return Double.parseDouble(next());
        }

        // Reads an entire line.
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
