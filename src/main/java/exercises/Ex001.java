package exercises;

import java.util.stream.IntStream;

public class Ex001 implements Exercise {

  @Override
  public void solve() {

    int sum = IntStream.range(1, 1_000)
            .filter(i -> i % 3 == 0 || i % 5 == 0)
            .sum();

    System.out.println(sum);
  }
}
