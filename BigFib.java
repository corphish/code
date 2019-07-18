import java.io.*;
import java.util.*;
import java.math.*;

public class BigFib {
    
    static long fib(long j, long k, long n) {
        long a = 1, b = 1, c = 1, d = 0, w = 1, x = 0, y = 0, z = 1, m = 7, p, q, t;
        for(m += 1e9; n > 0; n/= 2) {
            if (n % 2 > 0) {
                p = w;
                q = y;
                w = (a*w + c*x) % m;
                x = (b*p + d*x) % m;
                y = (a*y + c*z) % m;
                z = (b*q + d*z) % m;
            }
            p = a;
            q = c;
            t = b;
            a = (a*a + c*b) % m;
            b = (b*p + d*b) % m;
            c = (p*c + c*d) % m;
            d = (t*q + d*d) % m;
        }
        return (y*k + z*j) % m;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int t;
        long a,b,n;
        Scanner s = new Scanner(System.in);
        t = s.nextInt();
        while(t > 0) {
            a = s.nextLong();
            b = s.nextLong();
            n = s.nextLong();
            System.out.println(a + " " + b + " (" + n + ") -> " + fib(a,b,n));
            t--;
        }
    }
}