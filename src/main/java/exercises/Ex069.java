package exercises;

import static utilities.Utilities.phi;

public class Ex069 implements Exercise {

  private static final int N = 1_000_000;

  @Override
  public void solve() {

    double maxRatio = 0;
    int answer = 0;

    for (int i = 2; i <= N; i++) {
      double ratio = (double) i / (double) phi(i);
      if (ratio > maxRatio) {
        maxRatio = ratio;
        answer = i;
      }
    }

    System.out.println(answer);
  }
}
