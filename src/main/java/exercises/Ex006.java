package exercises;

import java.util.stream.LongStream;

public class Ex006 implements Exercise {

  @Override
  public void solve() {

    long n = 100;

    long sumOfSquares = LongStream.rangeClosed(1, n)
            .map(i -> i * i)
            .sum();

    long sum = LongStream.rangeClosed(1, n)
            .sum();

    long squareOfSum = sum * sum;

    System.out.println(squareOfSum - sumOfSquares);
  }
}
