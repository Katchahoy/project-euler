package exercises;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.iterators.PermutationIterator;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Ex024 implements Exercise {

  @Override
  public void solve() {

    int N = 1_000_000;

    List<Integer> digits = Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    Stream<List<Integer>> permutationStream = StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(new PermutationIterator<>(digits), Spliterator.NONNULL),
            false
    );

    permutationStream.map(Ex024::listToString)
            .sorted()
            .skip(N - 1)
            .findFirst()
            .ifPresent(System.out::println);
  }

  private static String listToString(List<Integer> digits) {
    StringBuilder builder = new StringBuilder();
    for (int i : digits) {
      builder.append(i);
    }
    return builder.toString();
  }

}
