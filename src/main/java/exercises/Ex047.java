package exercises;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.LongStream;

public class Ex047 implements Exercise {

  private static final long N = 4;

  @Override
  public void solve() {

    long i = 2;
    while (!testConsecutives(i)) {
      i++;
    }
    System.out.println(i);
  }

  private boolean testConsecutives(long n) {
    return LongStream.range(n, n + N).allMatch(this::countPrimeFactors);
  }

  private boolean countPrimeFactors(long n) {
    return primeFactors(n).size() == N;
  }

  private Set<Long> primeFactors(long n) {
    Set<Long> primeFactors = new HashSet<>();

    long i = n;
    long j = 2;

    while (j <= i / j) {

      if (i % j == 0) {
        primeFactors.add(j);
        i = i / j;

      } else {
        j++;
      }
    }
    primeFactors.add(i);

    return primeFactors;
  }
}
