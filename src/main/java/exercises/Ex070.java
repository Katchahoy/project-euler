package exercises;

import static utilities.Utilities.*;

public class Ex070 implements Exercise {

  private static final int N = 10_000_000;

  @Override
  public void solve() {
    double minRatio = Double.MAX_VALUE;
    int answer = 0;
    for (int i = 2; i < N; i++) {
      if (isPrime(i)) {
        continue;
      }
      int phi = phi(i);
      if (!isPermutation(Integer.toString(phi), Integer.toString(i))) {
        continue;
      }
      double ratio = (double) i / (double) phi;
      if (ratio < minRatio) {
        minRatio = ratio;
        answer = i;
      }
    }
    System.out.println(answer);
  }
}
