package exercises;

import java.util.Objects;
import java.util.stream.IntStream;

public class Ex206 implements Exercise {

  private static final long MIN = (long) Math.sqrt(1020304050607080900L);
  private static final long MAX = (long) Math.sqrt(1929394959697989990L);

  @Override
  public void solve() {

    for (long i = MIN; i <= MAX; i++) {
      long lastDigit = (i / 10) % 10;
      if (lastDigit != 3 && lastDigit != 7) {
        continue;
      }
      long p = i * i;
      String s = Long.toString(p);
      if (test(s)) {
        System.out.println(i);
        break;
      }
      i++;
    }
  }

  private boolean test(CharSequence s) {
    Objects.requireNonNull(s);

    if (s.length() != 19) {
      return false;
    }

    return IntStream.iterate(0, i -> i < s.length(), i -> i + 2)
            .allMatch(i -> s.charAt(i) - '0' == (i / 2 + 1) % 10);
  }
}
