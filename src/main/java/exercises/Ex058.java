package exercises;

import static utilities.Utilities.isPrime;

public class Ex058 implements Exercise {

  @Override
  public void solve() {

    long n = 1;
    long count = 0;
    long ratio;

    do {
      n += 2;
      long total = 2 * n - 1;
      long start = (long) Math.pow(n - 2, 2);
      long step = n - 1;

      for (int i = 0; i < 3; i++) {
        start += step;
        if (isPrime(start)) {
          count++;
        }
      }

      ratio = total / count;

    } while (ratio < 10);

    System.out.println(n);
  }
}
