package exercises;

import utilities.NumberDivisorType;

import java.util.HashSet;
import java.util.Set;

public class Ex023 implements Exercise {

  @Override
  public void solve() {

    int min = 12;
    int max = 28_123;

    Set<Integer> abundants = new HashSet<>();
    Set<Integer> sumsOfAbundant = new HashSet<>();

    for (int i = min; i < max; i++) {
      if (NumberDivisorType.ABUNDANT.equals(NumberDivisorType.resolve(i))) {
        Integer integer = i;
        abundants.add(integer);
        for (int abundant : abundants) {
          sumsOfAbundant.add(i + abundant);
        }
      }
    }

    int sum = 0;

    for (int i = 1; i < max; i++) {
      if (!sumsOfAbundant.contains(i)) {
        sum += i;
      }
    }

    System.out.println(sum);

  }

}
