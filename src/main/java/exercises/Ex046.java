package exercises;

import utilities.PrimeSupplier;

import java.util.stream.LongStream;

import static utilities.Utilities.isPrime;

public class Ex046 implements Exercise {

  @Override
  public void solve() {

    long i = 9;

    while (isPrime(i) || test(i)) {
      i += 2;
    }
    System.out.println(i);

  }

  private boolean test(long n) {
    return LongStream.generate(new PrimeSupplier())
            .takeWhile(i -> i < n)
            .filter(i -> canDecompose(n, i))
            .findFirst()
            .isPresent();
  }

  private boolean canDecompose(long n, long i) {
    return ((n - i) % 2 == 0 && isSquare((n - i) / 2));
  }

  private boolean isSquare(long n) {
    long sqrt = (long) Math.sqrt(n);
    return sqrt * sqrt == n;
  }
}
