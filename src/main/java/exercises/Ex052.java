package exercises;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ex052 implements Exercise {

  @Override
  public void solve() {

    IntStream.iterate(1, i -> i + 1)
            .dropWhile(i -> !test(i))
            .findFirst()
            .ifPresent(System.out::println);
  }

  private boolean test(int n) {
    int i = 2;
    while (i < 5) {
      if (!digits(n * i).equals(digits(n * (i + 1)))) {
        return false;
      }
      i++;
    }
    return true;

  }

  private List<Integer> digits(int n) {

    return Integer.toString(n)
            .chars()
            .sorted()
            .boxed()
            .collect(Collectors.toList());
  }
}
