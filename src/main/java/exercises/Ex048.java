package exercises;

import java.math.BigInteger;

public class Ex048 implements Exercise {

  static private final BigInteger N = BigInteger.valueOf(100_000_000_000L);
  static private final BigInteger P = BigInteger.valueOf(1000);

  @Override
  public void solve() {

    BigInteger sum = BigInteger.ZERO;
    BigInteger i = BigInteger.ONE;

    while (i.compareTo(P) <= 0) {
      BigInteger j = i.modPow(i, N);
      sum = sum.add(j).mod(N);
      i = i.add(BigInteger.ONE);
    }
    System.out.println(sum.mod(N));
  }
}
