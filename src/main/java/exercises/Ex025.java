package exercises;

import utilities.FibonacciSupplier2;

import java.util.stream.Stream;

public class Ex025 implements Exercise {

  @Override
  public void solve() {

    int N = 1000;

    long index = Stream.generate(new FibonacciSupplier2())
            .takeWhile(i -> i.toString().length() < N)
            .count();

    System.out.println(index + 1);
  }
}
