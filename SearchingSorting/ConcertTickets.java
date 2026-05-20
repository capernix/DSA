package SearchingSorting;
import java.io.*;
import java.util.*;

public class ConcertTickets {

    static FastReader in = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {

        int n = in.nextInt();
        int m = in.nextInt();

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {

            int customer = in.nextInt();

            Integer ticket = map.floorKey(customer);

            if (ticket == null) {
                sb.append(-1).append('\n');
            } else {

                sb.append(ticket).append('\n');

                int freq = map.get(ticket);

                if (freq == 1) {
                    map.remove(ticket);
                } else {
                    map.put(ticket, freq - 1);
                }
            }
        }

        out.print(sb);
        out.close();
    }

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
    }
}