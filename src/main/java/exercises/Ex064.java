package exercises;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Ex064 implements Exercise {

  private static final int N = 9999;

  @Override
  public void solve() {

    long count = IntStream.rangeClosed(9999, N)
            .filter(i -> !isSquare(i))
            .map(this::computePeriod)
            .filter(i -> i % 2 == 1)
            .count();

    System.out.println(count);
  }

  private int computePeriod(int n) {

    List<Integer> coefs = new ArrayList<>();
    BigDecimal sqrt = BigDecimal.valueOf(n).sqrt(MathContext.DECIMAL128);

    BigDecimal floor = sqrt.setScale(0, RoundingMode.FLOOR);
    BigDecimal remainder = BigDecimal.valueOf(n)
            .sqrt(MathContext.DECIMAL128)
            .subtract(floor);

    for (int i = 0; i < 50; i++) {
      BigDecimal inverse = BigDecimal.ONE.divide(remainder, 34, RoundingMode.HALF_UP);
      floor = inverse.setScale(0, RoundingMode.FLOOR);
      remainder = inverse.subtract(floor);
      coefs.add(floor.intValue());
    }

    return findPeriod(coefs);
  }

  private int findPeriod(List<Integer> coefs) {
    return 1;
  }

  private boolean isSquare(int n) {
    int sqrt = (int) Math.sqrt(n);
    return sqrt * sqrt == n;
  }
}
