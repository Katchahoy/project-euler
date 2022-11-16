package exercises;

import utilities.FibonacciSupplier;

import java.util.stream.LongStream;

public class Ex002 implements Exercise {

  @Override
  public void solve() {

    long max = 4_000_000L;

    long sum = LongStream.generate(new FibonacciSupplier())
            .takeWhile(i -> i <= max)
            .filter(i -> i % 2 == 0)
            .sum();

    System.out.println(sum);
  }

}
