package exercises;

import org.apache.commons.collections4.iterators.PermutationIterator;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ex061 implements Exercise {

  private final List<List<Map<String, List<String>>>> data = new ArrayList<>();

  public Ex061() {

    List<IntUnaryOperator> functions = Arrays.asList(
            GeometricNumber.TRIANGLE,
            GeometricNumber.SQUARE,
            GeometricNumber.PENTAGONAL,
            GeometricNumber.HEXAGONAL,
            GeometricNumber.HEPTAGONAL,
            GeometricNumber.OCTAGONAL
    );
    var iterator = new PermutationIterator<>(functions);
    while (iterator.hasNext()) {
      var currentFunctions = iterator.next();
      data.add(currentFunctions.stream().map(this::getNumbers).collect(Collectors.toList()));
    }
  }

  @Override
  public void solve() {

    if (data.isEmpty()) {
      return;
    }
    data.stream()
            .filter(maps -> {
              List<String> triangles = maps.get(0)
                      .values()
                      .stream()
                      .flatMap(List::stream)
                      .collect(Collectors.toList());

              return triangles.stream()
                      .anyMatch(reference -> {
                        boolean isMatch = find(maps, reference, reference, 0);
                        if (isMatch) {
                          System.out.println(reference);
                        }
                        return isMatch;
                      });
            }).findFirst()
            .ifPresent(maps -> System.out.println("success"));
  }

  private boolean find(List<Map<String, List<String>>> maps, String reference, String current, int i) {
    if (i + 1 >= maps.size()) {
      return reference.substring(0, 2).equals(current.substring(2));
    }
    var currentMap = maps.get(i + 1);
    List<String> nextList = currentMap.getOrDefault(current.substring(2), Collections.emptyList());
    return nextList.stream()
            .anyMatch(str -> {
              boolean isMatch = find(maps, reference, str, i + 1);
              if (isMatch) {
                System.out.println(str);
              }
              return isMatch;
            });
  }

  @Nonnull
  private Map<String, List<String>> getNumbers(IntUnaryOperator function) {

    return IntStream.iterate(1, i -> i + 1)
            .map(function)
            .dropWhile(n -> n < 1000)
            .takeWhile(n -> n < 10000)
            .mapToObj(Integer::toString)
            .filter(str -> str.charAt(2) != '0')
            .collect(Collectors.groupingBy(
                    str -> str.substring(0, 2),
                    HashMap::new,
                    Collectors.toList()
            ));
  }

  private enum GeometricNumber implements IntUnaryOperator {

    TRIANGLE(n -> n * (n + 1) / 2),
    SQUARE(n -> n * n),
    PENTAGONAL(n -> n * (3 * n - 1) / 2),
    HEXAGONAL(n -> n * (2 * n - 1)),
    HEPTAGONAL(n -> n * (5 * n - 3) / 2),
    OCTAGONAL(n -> n * (3 * n - 2));

    private final IntUnaryOperator function;

    GeometricNumber(IntUnaryOperator function) {
      this.function = function;
    }

    @Override
    public int applyAsInt(int operand) {
      return function.applyAsInt(operand);
    }
  }
}
