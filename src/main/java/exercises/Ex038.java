package exercises;

import java.util.Objects;
import java.util.stream.IntStream;

public class Ex038 implements Exercise {

  @Override
  public void solve() {

    IntStream.range(2, 10)
            .mapToLong(this::maxPandigital)
            .max()
            .ifPresent(System.out::println);
  }

  private long maxPandigital(int n) {

    int i = (int) Math.pow(10, 9 / (double) n - 1);

    CharSequence maxPandigital = new StringBuilder();
    CharSequence builder;
    while ((builder = tryPandigital(n, i++)).length() <= 9) {
      if (builder.length() == 9 && CharSequence.compare(builder, maxPandigital) > 0) {
        maxPandigital = builder;
      }
    }
    if (maxPandigital.length() == 9) {
      return Long.parseLong(maxPandigital, 0, 8, 10);
    }
    return 0;
  }

  private CharSequence tryPandigital(int n, int i) {
    StringBuilder builder = new StringBuilder();
    int j = 0;
    while (++j <= n && builder.append(i * j).length() <= 9) {
      if (builder.indexOf("0") >= 0 || hasDuplicates(builder)) {
        return new StringBuilder();
      }
    }
    return builder;
  }

  private boolean hasDuplicates(CharSequence str) {
    Objects.requireNonNull(str);

    return str.chars().distinct().count() < str.length();
  }
}
