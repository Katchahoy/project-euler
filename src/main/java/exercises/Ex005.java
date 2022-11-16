package exercises;

import java.util.stream.LongStream;

public class Ex005 implements Exercise {

  @Override
  public void solve() {

    long n = 20;

    LongStream.rangeClosed(2, n)
            .reduce(Ex005::lowestMultiple)
            .ifPresent(System.out::println);
  }

  private static long lowestMultiple(long a, long b) {

    long multiple = 1;

    long i = 2;
    while (i <= Math.max(a, b)) {

      if (a % i == 0 && b % i == 0) {
        multiple *= i;
        a = a / i;
        b = b / i;
        i = 2;

      } else if (a % i == 0) {
        multiple *= i;
        a = a / i;
        i = 2;

      } else if (b % i == 0) {
        multiple *= i;
        b = b / i;
        i = 2;

      } else {
        i++;
      }
    }

    return multiple;
  }
}
