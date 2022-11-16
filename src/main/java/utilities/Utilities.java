package utilities;

import it.unimi.dsi.fastutil.longs.Long2BooleanOpenHashMap;
import org.apache.commons.collections4.map.MultiKeyMap;

import java.math.BigInteger;
import java.util.*;
import java.util.function.LongPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.google.common.math.IntMath.gcd;

public class Utilities {

  public static int gcd(int n1, int n2) {
    if (n2 == 0) {
      return n1;
    }
    return gcd(n2, n1 % n2);
  }

  /**
   * https://fr.wikipedia.org/wiki/Indicatrice_d%27Euler
   */
  public static int phi(int n) {
    if (n == 1) {
      return 1;
    }
    int i = 2;
    while (i <= n / i) {
      if (n % i == 0) {
        int q = n / i;
        int gcd = gcd(i, q);
        return phi(i) * phi(q) * gcd / phi(gcd);
      }
      i++;
    }
    return n - 1;
  }

  public static int sumDigits(BigInteger value) {

    return value.toString()
            .chars()
            .map(i -> i - '0')
            .sum();
  }

  public static int sumOfDivisors(int n) {

    if (n < 1) {
      throw new IllegalArgumentException();
    }

    int i = 2;
    int sum = 1;

    while (i < n / i) {
      if (n % i == 0) {
        sum += (i + n / i);
      }
      i++;
    }

    if (i * i == n) {
      sum += i;
    }

    return sum;
  }

  public static boolean isPalindrome(int n) {

    String s = Integer.toString(n);
    int len = s.length();
    int i = 0;

    while (i < len / 2) {
      if (s.charAt(i) != s.charAt(len - 1 - i)) {
        return false;
      }
      i++;
    }

    return true;
  }

  public static boolean isPalindrome(CharSequence s) {
    Objects.requireNonNull(s);

    int len = s.length();

    return IntStream.range(0, len / 2)
            .allMatch(i -> s.charAt(i) == s.charAt(len - i - 1));
  }

  public static boolean isPermutation(CharSequence str, CharSequence ref) {
    if (str.length() != ref.length()) {
      return false;
    }
    LinkedList<Integer> refChars = ref.chars().boxed()
            .collect(Collectors.toCollection(LinkedList::new));
    for (int i = 0; i < str.length(); i++) {
      int c = str.charAt(i);
      if (!(refChars.contains(c))) {
        return false;
      }
      refChars.removeFirstOccurrence(c);
    }
    return true;
  }

  private static final Map<Long, Boolean> primeCache = new HashMap<>();

  public static boolean isPrime(long n) {

    if (n < 1) {
      throw new IllegalArgumentException();
    }

    if (primeCache.containsKey(n)) {
      return primeCache.get(n);
    }

    if (n == 1) {
      primeCache.put(n, false);
      return false;
    }

    long i = 2;
    while (i <= n / i) {
      if (n % i == 0) {
        primeCache.put(n, false);
        return false;
      }
      i++;
    }
    primeCache.put(n, true);
    return true;
  }

  public static class PrimePredicate implements LongPredicate {

    @Override
    public boolean test(long n) {

      if (n < 1) {
        throw new IllegalArgumentException();
      }

      boolean result;

      if (n == 1) {
        result = false;

      } else {
        result = true;
        long i = 2;
        while (i <= n / i) {
          if (n % i == 0) {
            result = false;
            break;
          }
          i++;
        }
      }

      return result;
    }
  }

  public static class LongPredicateWithCache implements LongPredicate {

    private final LongPredicate predicate;

    public LongPredicateWithCache(LongPredicate predicate) {
      this.predicate = predicate;
    }

    private final Long2BooleanOpenHashMap cache = new Long2BooleanOpenHashMap();

    @Override
    public boolean test(long n) {
      return cache.computeIfAbsent(n, predicate);
    }
  }
}
