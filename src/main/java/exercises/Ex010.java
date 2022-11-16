package exercises;

import utilities.PrimeSupplier;

import java.util.stream.LongStream;

public class Ex010 implements Exercise {

  @Override
  public void solve() {

    long n = 2_000_000L;

    long sum = LongStream.generate(new PrimeSupplier())
            .takeWhile(i -> i <= n)
            .sum();

    System.out.println(sum);
  }
}
