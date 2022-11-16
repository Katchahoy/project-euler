package exercises;

import java.math.BigInteger;

import static com.google.common.math.BigIntegerMath.binomial;

public class Ex053 implements Exercise {

  private static final int N = 100;

  private static final BigInteger P = BigInteger.valueOf(1_000_000);

  @Override
  public void solve() {

    int count = 0;

    int n = 1;
    while (n <= N) {

      int r = 0;
      while (r <= n) {

        BigInteger combination = binomial(n, r);
        if (combination.compareTo(P) > 0) {
          count++;
        }

        r++;
      }

      n++;
    }

    System.out.println(count);
  }
}
