package exercises;

import java.util.function.LongSupplier;
import java.util.stream.LongStream;

public class Ex012 implements Exercise {

  @Override
  public void solve() {

    int P = 500;

    LongStream.generate(new TriangleSupplier())
            .dropWhile(i -> countDivisors(i) < P)
            .findFirst()
            .ifPresent(System.out::println);
  }

  private static int countDivisors(long n) {

    int count = 0;
    int i = 1;

    while (i < n / i) {
      if (n % i == 0) {
        count += 2;
      }
      i++;
    }

    if (i == n / i && n % i == 0) {
      count++;
    }

    return count;
  }

  private static class TriangleSupplier implements LongSupplier {

    int value = 1;
    int cursor = 1;

    @Override
    public long getAsLong() {
      cursor++;
      long val = value;
      value += cursor;
      return val;
    }
  }
}
