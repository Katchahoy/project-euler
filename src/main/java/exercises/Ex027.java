package exercises;

import static com.google.common.math.LongMath.isPrime;

public class Ex027 implements Exercise {

  @Override
  public void solve() {

    long N = 1000;

    long maxCount = 0;
    long answer = 0;

    for (long a = -N; a < N; a++) {
      for (long b = -N; b < N; b++) {
        long n = 0;
        long current;
        while ((current = n * n + a * n + b) > 0 && isPrime(current)) {
          n++;
        }
        if (n > maxCount) {
          maxCount = n;
          answer = a * b;
        }
      }
    }

    System.out.println(answer);
  }
}
