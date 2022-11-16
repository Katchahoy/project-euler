package exercises;

import org.apache.commons.math3.fraction.BigFraction;

public class Ex057 implements Exercise {

  private static final int N = 1000;

  @Override
  public void solve() {

    int count = 0;
    BigFraction fraction = BigFraction.ONE;

    for (int i = 0; i < N; i++) {
      fraction = BigFraction.ONE
              .divide(fraction.add(BigFraction.ONE))
              .add(BigFraction.ONE);

      if (fraction.getNumerator().toString().length() > fraction.getDenominator().toString().length()) {
        count++;
      }
    }

    System.out.println(count);
  }
}
