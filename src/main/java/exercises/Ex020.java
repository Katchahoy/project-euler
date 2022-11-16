package exercises;

import java.math.BigInteger;

import static utilities.Utilities.sumDigits;

public class Ex020 implements Exercise {

  @Override
  public void solve() {

    System.out.println(sumDigits(factorial(BigInteger.valueOf(100L))));
  }

  private static BigInteger factorial(BigInteger n) {

    if (n == null || n.compareTo(BigInteger.ZERO) < 0) {
      throw new IllegalArgumentException();
    }

    if (BigInteger.ZERO.equals(n) || BigInteger.ONE.equals(n)) {
      return BigInteger.ONE;
    }

    return n.multiply(factorial(n.subtract(BigInteger.ONE)));
  }
}
