package exercises;

import com.google.common.base.Preconditions;
import com.google.common.math.LongMath;

import java.util.stream.IntStream;

public class Ex034 implements Exercise {

  static private final int range = 100_000;

  @Override
  public void solve() {

    long sum = IntStream.range(3, range)
            .filter(this::testSumOfFactorialOfDigits)
            .peek(System.err::println)
            .sum();

    System.out.println(sum);
  }

  private boolean testSumOfFactorialOfDigits(int n) {
    Preconditions.checkArgument(n >= 0);

    long sum = Long.toString(n)
            .chars()
            .map(i -> i - '0')
            .mapToLong(LongMath::factorial)
            .sum();

    return sum == n;
  }
}
