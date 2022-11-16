package exercises;

import utilities.Utilities;
import utilities.Utilities.LongPredicateWithCache;

import java.util.function.LongPredicate;
import java.util.stream.LongStream;

public class Ex037 implements Exercise {

  private LongPredicate primePredicate = new LongPredicateWithCache(Utilities::isPrime);

  @Override
  public void solve() {

    long sum = LongStream.iterate(1, i -> i + 1)
            .filter(this::isBothWaysTruncatablePrime)
            .skip(4)
            .limit(11)
            .sum();

    System.out.println(sum);
  }

  private boolean isBothWaysTruncatablePrime(long n) {
    String str = Long.toString(n);
    int i = 0;
    while (i < str.length()) {
      if (isNotPrime(str.substring(0, i)) || isNotPrime(str.substring(i))) {
        return false;
      }
      i++;
    }
    return true;
  }

  private boolean isNotPrime(String substring) {
    if (substring.isEmpty()) {
      return false;
    }
    int sub1 = Integer.parseInt(substring);
    return !primePredicate.test(sub1);
  }
}
