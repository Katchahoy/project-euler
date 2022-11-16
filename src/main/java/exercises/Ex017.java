package exercises;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.stream.IntStream;

public class Ex017 implements Exercise {

  private static final Map<Integer, String> cardinals = ImmutableMap.<Integer, String>builder()
          .put(1, "one")
          .put(2, "two")
          .put(3, "three")
          .put(4, "four")
          .put(5, "five")
          .put(6, "six")
          .put(7, "seven")
          .put(8, "eight")
          .put(9, "nine")
          .put(10, "ten")
          .put(11, "eleven")
          .put(12, "twelve")
          .put(13, "thirteen")
          .put(14, "fourteen")
          .put(15, "fifteen")
          .put(16, "sixteen")
          .put(17, "seventeen")
          .put(18, "eighteen")
          .put(19, "nineteen")
          .put(20, "twenty")
          .put(30, "thirty")
          .put(40, "forty")
          .put(50, "fifty")
          .put(60, "sixty")
          .put(70, "seventy")
          .put(80, "eighty")
          .put(90, "ninety")
          .put(100, "hundred")
          .put(1000, "thousand")
          .build();

    @Override
    public void solve() {

    long sum = IntStream.rangeClosed(1, 1000)
            .mapToObj(Ex017::asLetters)
            .mapToLong(Ex017::countLetters)
            .sum();

    System.out.println(sum);
  }

  private static long countLetters(CharSequence str) {
    return str.chars().filter(i-> i >= 'a' && i <= 'z').count();
  }

  private static CharSequence asLetters(int n) {
    Preconditions.checkArgument(n > 0);

    StringBuilder builder = new StringBuilder();

    int thousands = n / 1000;
    if (thousands > 0) {
      builder.append(cardinals.get(thousands))
              .append(" ")
              .append(cardinals.get(1000));
    }

    int hundreds = (n - thousands * 1000) / 100;
    if (hundreds > 0) {
      builder.append(cardinals.get(hundreds))
              .append(" ")
              .append(cardinals.get(100));
    }

    int tensUnits = (n - thousands * 1000 - hundreds * 100);
    if (tensUnits == 0) {
      return builder;
    }

    if (thousands > 0 || hundreds > 0) {
      builder.append(" and ");
    }

    if (tensUnits <= 20 || tensUnits % 10 == 0) {
      builder.append(cardinals.get(tensUnits));

    } else {
      builder.append(cardinals.get((tensUnits / 10) * 10))
              .append("-")
              .append(cardinals.get(tensUnits % 10));
    }

    return builder;
  }
}
