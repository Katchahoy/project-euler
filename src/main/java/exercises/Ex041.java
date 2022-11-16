package exercises;

import org.apache.commons.collections4.iterators.PermutationIterator;
import utilities.Utilities;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class Ex041 implements Exercise {

  @Override
  public void solve() {

    IntStream.rangeClosed(1, 9)
            .mapToObj(i -> IntStream.rangeClosed(1, 10 - i).boxed().collect(Collectors.toList()))
            .map(PermutationIterator::new)
            .map(iterator -> Spliterators.spliteratorUnknownSize(iterator, Spliterator.NONNULL))
            .flatMap(spliterator -> StreamSupport.stream(spliterator, false))
            .mapToLong(this::convert)
            .filter(Utilities::isPrime)
            .max()
            .ifPresent(System.out::println);
  }

  private long convert(List<Integer> digits) {
    int n = 0;
    for (int i : digits) {
      n = 10 * n + i;
    }
    return n;
  }
}
