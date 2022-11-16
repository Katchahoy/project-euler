package exercises;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Ex026 implements Exercise {

  private static final int scale = 2000;

  private final Map<BigDecimal, Integer> cache = new TreeMap<>();

  @Override
  public void solve() {

    Stream.iterate(BigDecimal.valueOf(2), i -> i.add(BigDecimal.ONE))
            .limit(1000)
            .max(Comparator.comparing(this::maxLength))
            .ifPresent(System.out::println);
  }

  private int maxLength(BigDecimal nb) {

    if (cache.containsKey(nb)) {
      return cache.get(nb);
    }

    BigDecimal fraction = BigDecimal.ONE.divide(nb, scale, RoundingMode.FLOOR);
    String rev = StringUtils.reverse(fraction.toString().substring(2));
    int i = 1;

    while (i < rev.length()) {

      String sub = rev.substring(0, i);
      String[] split = rev.split(sub);

      long count = Arrays.stream(split)
              .map(String::length)
              .filter(len -> len == 0)
              .count();

      if (split.length - count <= 1 && StringUtils.countMatches(rev, sub) >= 2) {
        cache.put(nb, sub.length());
        return sub.length();
      }

      i++;
    }

    cache.put(nb, 0);
    return 0;
  }
}
