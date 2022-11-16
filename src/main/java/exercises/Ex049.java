package exercises;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.tuple.Pair;
import utilities.PrimeSupplier;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Ex049 implements Exercise {

  private static final int MIN = 1000;
  private static final int MAX = 10000;

  @Override
  public void solve() {


    var map = LongStream
            .generate(new PrimeSupplier(MIN))
            .takeWhile(i -> i < MAX)
            .boxed()
            .collect(Collectors.toMap(
                    this::digitList,
                    i -> new StringBuilder().append(i),
                    StringBuilder::append)
            );

    map.values().forEach(this::hasArithmeticSequence);

  }

  private boolean hasArithmeticSequence(CharSequence builder) {
    Preconditions.checkArgument(builder.length() % 4 == 0);

    if (builder.length() < 12) {
      return false;
    }

    int[] numbers = IntStream.range(0, builder.length() / 4)
            .map(i -> Integer.parseInt(builder, 4 * i, 4 * (i + 1), 10))
            .toArray();

    Map<Pair<Integer, Integer>, Integer> diffMap = new HashMap<>();
    Set<Integer> diffSet = new HashSet<>();
    for (int i = 0; i < numbers.length - 1; i++) {
      for (int j = i + 1; j < numbers.length; j++) {
        int n1 = numbers[i];
        int n2 = numbers[j];
        Pair<Integer, Integer> pair = Pair.of(n1, n2);
        diffMap.put(pair, n2 - n1);
        if (!diffSet.add(n2 - n1) && testPairs(diffMap, pair)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean testPairs(Map<Pair<Integer, Integer>, Integer> diffMap, Pair<Integer, Integer> pair) {

    for (var entry : diffMap.entrySet()) {
      Pair<Integer, Integer> key = entry.getKey();
      Integer diff = entry.getValue();

      if (!key.equals(pair)
              && diff == pair.getRight() - pair.getLeft()
              && key.getRight().equals(pair.getLeft())) {
        System.out.println(key.getLeft() + " " + key.getRight() + " " + pair.getRight());
        return true;
      }
    }
    return false;
  }

  private List<Integer> digitList(long n) {

    return Long.toString(n)
            .chars()
            .map(i -> i - '0')
            .boxed()
            .sorted()
            .collect(Collectors.toList());
  }
}
