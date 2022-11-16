package exercises;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import static utilities.Utilities.isPrime;

public class Ex035 implements Exercise {

  private static final int N = 1_000_000;

  @Override
  public void solve() {

    long count = IntStream.range(2, N)
            .filter(this::isCircularPrime)
            .distinct()
            .count();

    System.out.println(count);
  }

  private boolean isCircularPrime(int n) {

    int len = Integer.toString(n).length();

    return rotationStream(n)
            .allMatch(i -> Integer.toString(i).length() == len && isPrime(i));
  }

  private IntStream rotationStream(int n) {

    return StreamSupport.intStream(Spliterators.spliteratorUnknownSize(
            new RotationIterator(n), Spliterator.DISTINCT), false
    );
  }

  private static class RotationIterator implements PrimitiveIterator.OfInt {

    int[] digits;
    int offset = 0;

    RotationIterator(int n) {
      digits = Integer.toString(n).chars().map(i -> i - '0').toArray();
    }

    private static int convert(int[] digits) {
      int n = 0;
      for (int i : digits) {
        n = 10 * n + i;
      }
      return n;
    }

    @Override
    public boolean hasNext() {
      return offset < digits.length;
    }

    @Override
    public int nextInt() {
      int[] val = Arrays.copyOf(digits, digits.length);
      ArrayUtils.shift(val, offset++);
      return convert(val);
    }
  }
}
