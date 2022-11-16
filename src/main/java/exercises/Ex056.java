package exercises;

import java.math.BigInteger;

public class Ex056 implements Exercise {

  private static final int maxA = 100;
  private static final int maxB = 100;

  @Override
  public void solve() {

    long maxSum = 0;
    for (int a = 0; a < maxA; a++) {
      for (int b = 0; b < maxB; b++) {
        BigInteger pow = BigInteger.valueOf(a).pow(b);
        long sumDigits = pow.toString().chars().map(i -> i - '0').sum();
        if (sumDigits > maxSum) {
          maxSum = sumDigits;
        }
      }
    }

    System.out.println(maxSum);
  }
}
