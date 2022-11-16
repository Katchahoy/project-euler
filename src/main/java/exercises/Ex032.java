package exercises;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.iterators.PermutationIterator;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Ex032 implements Exercise {

  @Override
  public void solve() {

    List<Integer> digits = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    var tripletStream = StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(new PermutationIterator<>(digits), Spliterator.SIZED),
            false
    );

    int sum = tripletStream
            .flatMap(this::getTriplets)
            .filter(this::isTripletProduct)
            .mapToInt(value -> value.get(2))
            .distinct()
            .sum();

    System.out.println(sum);
  }

  private boolean isTripletProduct(List<Integer> numbers) {
    Objects.requireNonNull(numbers);

    return numbers.size() == 3
            && numbers.get(0) * numbers.get(1) == numbers.get(2);
  }

  private Stream<List<Integer>> getTriplets(List<Integer> digits) {

    return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
            new PandigitalIterator(digits), Spliterator.SIZED),
            false
    );
  }

  private static class PandigitalIterator implements Iterator<List<Integer>> {

    List<Integer> digits;
    int s1 = 1; // start position of 2nd number
    int s2 = 2; // start position of 3rd number

    PandigitalIterator(List<Integer> digits) {
      Objects.requireNonNull(digits);
      this.digits = digits;
    }

    @Override
    public boolean hasNext() {
      return s2 < digits.size() && s1 < digits.size() - 1;
    }

    @Override
    public List<Integer> next() {
      int i = 0;
      List<Integer> numbers = new ArrayList<>();

      int N1 = 0;
      while (i < s1) {
        N1 = N1 * 10 + digits.get(i);
        i++;
      }
      numbers.add(N1);

      int N2 = 0;
      while (i < s2) {
        N2 = N2 * 10 + digits.get(i);
        i++;
      }
      numbers.add(N2);

      int N3 = 0;
      while (i < digits.size()) {
        N3 = N3 * 10 + digits.get(i);
        i++;
      }
      numbers.add(N3);

      s2++;
      if (s2 > digits.size() - 1) {
        s1++;
        s2 = s1 + 1;
      }

      numbers.sort(Comparator.naturalOrder());
      return numbers;
    }
  }
}
